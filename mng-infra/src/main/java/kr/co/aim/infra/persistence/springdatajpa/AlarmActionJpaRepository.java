package kr.co.aim.infra.persistence.springdatajpa;

import kr.co.aim.domain.model.AlarmAction;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AlarmActionJpaRepository extends JpaRepository<AlarmAction, Long> {
    List<AlarmAction> findByAlarmDefId(Long alarmIDefId);
}
