package kr.co.aim.infra.persistence.springdatajpa;

import kr.co.aim.infra.persistence.entity.CarriersDefEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CarriersDefJpaRepository extends JpaRepository<CarriersDefEntity, Long> {
}
