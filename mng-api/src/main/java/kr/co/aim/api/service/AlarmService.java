package kr.co.aim.api.service;

import kr.co.aim.common.format.AlarmReportBody;
import kr.co.aim.common.format.request.BaseMessage;
import kr.co.aim.common.handler.NotificationHandler;
import kr.co.aim.domain.model.Alarm;
import kr.co.aim.domain.model.AlarmAction;
import kr.co.aim.domain.model.AlarmDef;
import kr.co.aim.domain.repository.AlarmActionRepository;
import kr.co.aim.domain.repository.AlarmDefRepository;
import kr.co.aim.domain.repository.AlarmRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
@RequiredArgsConstructor // final 필드에 대한 생성자를 자동으로 만들어줍니다. (DI)
@Slf4j
public class AlarmService {

    private final AlarmDefRepository alarmDefRepository; // 구현체(Infra)가 아닌 인터페이스(Domain)에 의존
    private final AlarmRepository alarmRepository;
    private final AlarmActionRepository alarmActionRepository;

    private final Map<String, NotificationHandler> notificationServices;


    // ============== [확인용 코드 추가] ==============
    //    @PostConstruct
    //    public void checkProxy() {
    //        log.info("### Injected AlarmRepository Class: {}", alarmRepository.getClass().getName());
    //    }
    // ===============================================

    /**
     * 알람을 기록합니다.
     * 1. 알람 정의 find by AlarmCode
     * 만일 알람 정의가 없다면 종료
     * <p>
     * 2. 설비id 와 alarmCode 로 Alarm find
     * 만일 없다면, 생성
     * 있다면, 변경
     *
     * @param message 받은 메시지
     */
    @Transactional // 이 메소드가 하나의 트랜잭션으로 동작하도록 보장합니다.
    public void alarmReport(BaseMessage<AlarmReportBody> message) {
        // 1. alarmDefRepository 통해 AlarmDef Domain 객체를 가져온다.
        Optional<AlarmDef> alarmDefOptional = alarmDefRepository.findByAlarmCodeName(message.getBody().getAlarmCode());
        if(alarmDefOptional.isEmpty())
        {
            log.info("alarm Def not Exists");
            return;
        }
        AlarmDef alarmDef = alarmDefOptional.get();

        // 2. AlarmDefId 와 설비Id를 통해  Optional<Alarm> alarm 을 찾는다.
        // 위 데이터는 없을수도 있고 있을수도 있음
        // 만일 없다면, 생성
        // 있다면 변경한다.

        // 도메인 객체를 찾거나, 없으면 새로 생성해서 로직을 위임
        // TODO: 현재는 equipmentId 에 1L 강제 하드코딩 equipemts 테이블이 생성됨에 따라 해당 로직 수정
        Alarm alarm = alarmRepository.findByAlarmDefIdAndEquipmentId(alarmDef.getId(),1L)
            .map(existingAlarm -> {
                existingAlarm.updateAlarm(message); // 2. 기존 알람에 상태 변경을 위임
                return existingAlarm;
            }).orElseGet(() -> Alarm.issue(alarmDef.getId(), 1L, message)); // 1. 새 알람 생성을 위임

        // 3. Repository를 통해 변경된 Domain 객체를 저장한다.
        // @Transactional 어노테이션의 '변경 감지(Dirty Checking)' 기능 덕분에
        // 이 save 호출은 사실 생략 가능할 때도 있지만, 명시적으로 호출하는 것이 좋습니다.
        alarmRepository.save(alarm);

        // TODO: Alarm Action 현재 Alarm Action이 users 테이블이 없어서 강제로 유저 하드코딩 중 추후 수정
        List<AlarmAction> alarmActionList = alarmActionRepository.findByAlarmDefId(alarmDef.getId());
        for(AlarmAction alarmAction : alarmActionList){
            alarmAction.execute(notificationServices);
        }

    }
}