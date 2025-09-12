package kr.co.aim.infra.persistence.adapter;

import kr.co.aim.common.annotation.PublishHistoryEvent;
import kr.co.aim.domain.model.AlarmAction;
import kr.co.aim.domain.repository.AlarmActionRepository;
import kr.co.aim.infra.persistence.springdatajpa.AlarmActionJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * UserRepository의 JPA 기반 구현체.
 * 실제 DB 작업은 Spring Data JPA가 제공하는 JpaRepository에 위임합니다.
 */

@Repository
@RequiredArgsConstructor
public class AlarmActionRepositoryImpl implements AlarmActionRepository {
    // Spring Data JPA가 자동으로 구현해주는 JPA 리포지토리. UserEntity를 다룬다.
    private final AlarmActionJpaRepository alarmActionJpaRepository;

    @Override
    public List<AlarmAction> findAll() {
        return alarmActionJpaRepository.findAll();
    }

    @Override
    public Optional<AlarmAction> findById(Long id) {
        return Optional.empty();
    }

    @Override
    public List<AlarmAction> findByAlarmDefId(Long alarmDefId) {
        return alarmActionJpaRepository.findByAlarmDefId(alarmDefId);
    }

    @PublishHistoryEvent
    @Override
    public AlarmAction save(AlarmAction alarmAction) {
        return alarmActionJpaRepository.save(alarmAction);
    }

}
