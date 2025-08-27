package kr.co.aim.api.service;

import kr.co.aim.domain.model.User;
import kr.co.aim.domain.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor // final 필드에 대한 생성자를 자동으로 만들어줍니다. (DI)
public class UserService {

    private final UserRepository userRepository; // 구현체(Infra)가 아닌 인터페이스(Domain)에 의존

    /**
     * 사용자의 이름을 변경합니다.
     * @param userId 사용자의 ID
     * @param newUsername 새로운 사용자 이름
     * @return 이름이 변경된 사용자 도메인 객체
     */
    @Transactional // 이 메소드가 하나의 트랜잭션으로 동작하도록 보장합니다.
    public User changeUsername(Long userId, String newUsername) {
        // 1. Repository를 통해 Domain 객체를 가져온다.
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 사용자입니다. ID: " + userId));

        // 2. Domain 객체에 작업을 위임한다. (가장 중요한 부분!)
        // 서비스가 직접 user.setUsername(..)을 호출하는 것이 아니라,
        // User 도메인 객체가 스스로 자신의 상태를 바꾸도록 메시지를 보냅니다.
        user.changeUsername(newUsername);

        // 3. Repository를 통해 변경된 Domain 객체를 저장한다.
        // @Transactional 어노테이션의 '변경 감지(Dirty Checking)' 기능 덕분에
        // 이 save 호출은 사실 생략 가능할 때도 있지만, 명시적으로 호출하는 것이 좋습니다.
        return userRepository.save(user);
    }
}