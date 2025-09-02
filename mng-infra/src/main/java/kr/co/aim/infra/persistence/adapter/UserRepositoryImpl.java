package kr.co.aim.infra.persistence.adapter;

import kr.co.aim.domain.model.User;
import kr.co.aim.domain.repository.UserRepository;
import kr.co.aim.infra.persistence.entity.UserEntity;
import kr.co.aim.infra.persistence.mapper.UserMapper;
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
public class UserRepositoryImpl implements UserRepository {
    // Spring Data JPA가 자동으로 구현해주는 JPA 리포지토리. UserEntity를 다룬다.
    private final UserJpaRepository userJpaRepository;
    private final UserMapper userMapper;

    @Override
    public User save(User user) {
        // 1. Domain -> Entity 변환
        UserEntity entity = userMapper.toEntity(user);
        // 2. JPA 리포지토리를 통해 DB에 저장
        UserEntity savedEntity = userJpaRepository.save(entity);
        // 3. 저장된 Entity -> Domain 변환 후 반환
        return userMapper.toDomain(savedEntity);
    }

    @Override
    public Optional<User> findById(Long id) {
        // 1. JPA 리포지토리를 통해 ID로 Entity 조회
        Optional<UserEntity> entityOptional = userJpaRepository.findById(id);
        // 2. 조회된 Optional<Entity>를 Optional<Domain>으로 변환하여 반환
        return entityOptional.map(userMapper::toDomain);
    }

    @Override
    public Optional<User> findByEmail(String email) {
        // 1. JPA 리포지토리를 통해 Email로 Entity 조회
        Optional<UserEntity> entityOptional = userJpaRepository.findByEmail(email);
        // 2. 조회된 Optional<Entity>를 Optional<Domain>으로 변환하여 반환
        return entityOptional.map(userMapper::toDomain);
    }

    @Override
    public List<User> findAll() {
        // 1. JPA 리포지토리를 통해 모든 UserEntity 조회
        List<UserEntity> entities = userJpaRepository.findAll();
        // 2. Entity 리스트를 Domain 객체 리스트로 변환하여 반환
        return entities.stream()
                .map(userMapper::toDomain)
                .collect(Collectors.toList());
    }
}
