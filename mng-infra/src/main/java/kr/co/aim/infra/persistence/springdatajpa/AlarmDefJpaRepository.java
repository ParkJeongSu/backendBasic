package kr.co.aim.infra.persistence.springdatajpa;

import kr.co.aim.infra.persistence.entity.AlarmDefEntity;
import kr.co.aim.infra.persistence.entity.CarriersEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AlarmDefJpaRepository extends JpaRepository<AlarmDefEntity, Long> {
    List<AlarmDefEntity> findByAlarmCodeName(String alarmCodeName);
}
