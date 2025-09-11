package kr.co.aim.infra.listener;

import jakarta.persistence.PostPersist;
import jakarta.persistence.PostRemove;
import jakarta.persistence.PostUpdate;
import kr.co.aim.common.Utils.BeanUtils;
import kr.co.aim.infra.persistence.entity.AlarmEntity;
import kr.co.aim.infra.persistence.entity.AlarmHistoryEntity;
import kr.co.aim.infra.persistence.springdatajpa.AlarmHistoryJpaRepository;

public class AlarmHistoryListener {

    // AlarmEntity가 생성되거나 업데이트된 후에 이 메소드가 호출됩니다.
    @PostPersist
    @PostUpdate
    @PostRemove
    public void createHistory(AlarmEntity alarmEntity) {
        // Spring Bean을 직접 가져와서 사용합니다.
        AlarmHistoryJpaRepository alarmHistoryRepository = BeanUtils.getBean(AlarmHistoryJpaRepository.class);

        // History Entity 생성
        AlarmHistoryEntity history = AlarmHistoryEntity.builder()
                .alarmDefId(alarmEntity.getAlarmDefId())
                .equipmentId(alarmEntity.getEquipmentId())
                .alarmState(alarmEntity.getAlarmState())
                .createTime(alarmEntity.getCreateTime())
                .clearTime(alarmEntity.getClearTime())
                .timeKey(alarmEntity.getTimeKey())
                .eventName(alarmEntity.getEventName())
                .eventTime(alarmEntity.getEventTime())
                .eventUser(alarmEntity.getEventUser())
                .eventComment(alarmEntity.getEventComment())
                .build();

        alarmHistoryRepository.save(history);
    }
}