package kr.co.aim.infra.persistence.adapter;

import kr.co.aim.domain.model.AlarmDef;
import kr.co.aim.domain.model.Carriers;
import kr.co.aim.domain.repository.AlarmDefRepository;
import kr.co.aim.domain.repository.CarrierRepository;
import kr.co.aim.infra.persistence.entity.AlarmDefEntity;
import kr.co.aim.infra.persistence.entity.CarriersEntity;
import kr.co.aim.infra.persistence.mapper.AlarmDefMapper;
import kr.co.aim.infra.persistence.mapper.CarriersMapper;
import kr.co.aim.infra.persistence.springdatajpa.AlarmDefJpaRepository;
import kr.co.aim.infra.persistence.springdatajpa.AlarmJpaRepository;
import kr.co.aim.infra.persistence.springdatajpa.CarriersJpaRepository;
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
public class AlarmDefRepositoryImpl implements AlarmDefRepository {
    // Spring Data JPA가 자동으로 구현해주는 JPA 리포지토리. UserEntity를 다룬다.
    private final AlarmDefJpaRepository alarmDefJpaRepository;
    private final AlarmDefMapper alarmDefMapper;

    @Override
    public List<AlarmDef> findAll() {
        List<AlarmDefEntity> alarmDefEntities = alarmDefJpaRepository.findAll();
        return alarmDefEntities.stream().map(alarmDefMapper::toDomain).collect(Collectors.toList());
    }

    @Override
    public Optional<AlarmDef> findById(Long id) {
        return Optional.empty();
    }

    @Override
    public Optional<AlarmDef> findByAlarmCodeName(String alarmCodeName) {
        Optional<AlarmDefEntity> alarmDefEntityOptional = alarmDefJpaRepository.findByAlarmCodeName(alarmCodeName).stream().findFirst();
        return alarmDefEntityOptional.map(alarmDefMapper::toDomain);
    }

    @Override
    public AlarmDef save(AlarmDef alarmDef) {
        AlarmDefEntity entity = alarmDefMapper.toEntity(alarmDef);
        AlarmDefEntity savedEntity = alarmDefJpaRepository.save(entity);
        return alarmDefMapper.toDomain(savedEntity);
    }

}
