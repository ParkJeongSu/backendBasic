package kr.co.aim.infra.persistence.springdatajpa;

import kr.co.aim.infra.persistence.entity.CarriersEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CarriersJpaRepository extends JpaRepository<CarriersEntity, Long> {
    List<CarriersEntity> findByReservedFalseAndErrorFalse();
}
