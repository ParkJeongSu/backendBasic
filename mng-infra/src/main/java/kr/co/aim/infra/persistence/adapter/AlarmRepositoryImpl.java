package kr.co.aim.infra.persistence.adapter;

import kr.co.aim.domain.model.Alarm;
import kr.co.aim.domain.repository.AlarmRepository;
import kr.co.aim.infra.persistence.entity.AlarmEntity;
import kr.co.aim.infra.persistence.mapper.AlarmMapper;
import kr.co.aim.infra.persistence.springdatajpa.AlarmJpaRepository;
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
public class AlarmRepositoryImpl implements AlarmRepository {
    // Spring Data JPA가 자동으로 구현해주는 JPA 리포지토리. UserEntity를 다룬다.
    private final AlarmJpaRepository alarmJpaRepository;
    private final AlarmMapper alarmMapper;


    @Override
    public List<Alarm> findAll() {
        List<AlarmEntity> alarmEntities = alarmJpaRepository.findAll();
        return alarmEntities.stream().map(alarmMapper::toDomain).collect(Collectors.toList());
    }

    @Override
    public Optional<Alarm> findById(Long id) {
        return Optional.empty();
    }

    @Override
    public Optional<Alarm> findByAlarmDefIdAndEquipmentId(Long alarmDefId, Long equipmentId) {
        Optional<AlarmEntity> alarmEntityOptional = alarmJpaRepository.findByAlarmDefIdAndEquipmentId(alarmDefId,equipmentId).stream().findFirst();
        return alarmEntityOptional.map(alarmMapper::toDomain);
    }

    @Override
    public Alarm save(Alarm alarm) {
        AlarmEntity entity = alarmMapper.toEntity(alarm);
        AlarmEntity savedEntity = alarmJpaRepository.save(entity);
        return alarmMapper.toDomain(savedEntity);
    }
}
