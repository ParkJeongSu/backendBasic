package kr.co.aim.api.event.listener;

import kr.co.aim.api.event.impl.AlarmHistoryEvent;
import kr.co.aim.domain.model.Alarm;
import kr.co.aim.infra.persistence.entity.AlarmHistoryEntity;
import kr.co.aim.infra.persistence.springdatajpa.AlarmHistoryJpaRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class HistoryEventListener {
    private final AlarmHistoryJpaRepository alarmHistoryRepository;

    @EventListener
    public void handleAlarmHistory(AlarmHistoryEvent event) {
//        log.info("Recording history for Alarm ID: {}", event.getAlarm().getId());
        Alarm alarm = event.getAlarm();

        // History Entity 생성
        AlarmHistoryEntity history = AlarmHistoryEntity.builder()
                .alarmDefId(alarm.getAlarmDefId())
                .equipmentId(alarm.getEquipmentId())
                .alarmState(alarm.getAlarmState())
                .createTime(alarm.getCreateTime())
                .clearTime(alarm.getClearTime())
                .timeKey(alarm.getTimeKey())
                .eventName(alarm.getEventName())
                .eventTime(alarm.getEventTime())
                .eventUser(alarm.getEventUser())
                .eventComment(alarm.getEventComment())
                .build();

        alarmHistoryRepository.save(history);
    }
}
