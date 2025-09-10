package kr.co.aim.domain.model;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor//(access = AccessLevel.PROTECTED) // JPA Entity 등을 위한 기본 생성자
@AllArgsConstructor
public class User {
    private Long id;
    private String username;
    private String email;

    // 비즈니스 로직 예시
    public void changeUsername(String newUsername) {
        if (newUsername == null || newUsername.isBlank()) {
            throw new IllegalArgumentException("사용자 이름은 비워둘 수 없습니다.");
        }
        this.username = newUsername;
    }
}