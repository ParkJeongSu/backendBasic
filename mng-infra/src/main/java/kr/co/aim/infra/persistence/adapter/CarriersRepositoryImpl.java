package kr.co.aim.infra.persistence.adapter;

import kr.co.aim.domain.model.Carriers;
import kr.co.aim.domain.model.User;
import kr.co.aim.domain.repository.CarrierRepository;
import kr.co.aim.infra.persistence.entity.CarriersEntity;
import kr.co.aim.infra.persistence.entity.UserEntity;
import kr.co.aim.infra.persistence.mapper.CarriersMapper;
import kr.co.aim.infra.persistence.mapper.UserMapper;
import kr.co.aim.infra.persistence.springdatajpa.CarriersJpaRepository;
import kr.co.aim.infra.persistence.springdatajpa.UserJpaRepository;
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
public class CarriersRepositoryImpl implements CarrierRepository {
    // Spring Data JPA가 자동으로 구현해주는 JPA 리포지토리. UserEntity를 다룬다.
    private final CarriersJpaRepository carriersJpaRepository;
    private final CarriersMapper carriersMapper;

    @Override
    public Carriers save(Carriers carriers) {
        // 1. Domain -> Entity 변환
        CarriersEntity entity = carriersMapper.toEntity(carriers);
        // 2. JPA 리포지토리를 통해 DB에 저장
        CarriersEntity savedEntity = carriersJpaRepository.save(entity);
        // 3. 저장된 Entity -> Domain 변환 후 반환
        return carriersMapper.toDomain(savedEntity);
    }

    @Override
    public Optional<Carriers> findById(Long id) {
        // 1. JPA 리포지토리를 통해 ID로 Entity 조회
        Optional<CarriersEntity> entityOptional = carriersJpaRepository.findById(id);
        // 2. 조회된 Optional<Entity>를 Optional<Domain>으로 변환하여 반환
        return entityOptional.map(carriersMapper::toDomain);
    }

    @Override
    public List<Carriers> findAll() {
        // 1. JPA 리포지토리를 통해 모든 UserEntity 조회
        List<CarriersEntity> entities = carriersJpaRepository.findAll();
        // 2. Entity 리스트를 Domain 객체 리스트로 변환하여 반환
        return entities.stream()
                .map(carriersMapper::toDomain)
                .collect(Collectors.toList());
    }
}
