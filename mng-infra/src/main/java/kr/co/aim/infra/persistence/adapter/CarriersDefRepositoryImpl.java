package kr.co.aim.infra.persistence.adapter;

import kr.co.aim.domain.model.CarrierDef;
import kr.co.aim.domain.repository.CarrierDefRepository;
import kr.co.aim.infra.persistence.entity.CarriersDefEntity;
import kr.co.aim.infra.persistence.mapper.CarrierDefMapper;
import kr.co.aim.infra.persistence.springdatajpa.CarriersDefJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;

/**
 * UserRepository의 JPA 기반 구현체.
 * 실제 DB 작업은 Spring Data JPA가 제공하는 JpaRepository에 위임합니다.
 */

@Repository
@RequiredArgsConstructor
public class CarriersDefRepositoryImpl implements CarrierDefRepository {
    // Spring Data JPA가 자동으로 구현해주는 JPA 리포지토리. UserEntity를 다룬다.

    private final CarriersDefJpaRepository carriersDefJpaRepository;
    private final CarrierDefMapper carrierDefMapper;

    @Override
    public List<CarrierDef> findAll() {
        // 1. JPA 리포지토리를 통해 모든 UserEntity 조회
        List<CarriersDefEntity> entities = carriersDefJpaRepository.findAll();
        // 2. Entity 리스트를 Domain 객체 리스트로 변환하여 반환
        return entities.stream()
                .map(carrierDefMapper::toDomain)
                .collect(Collectors.toList());
    }
}
