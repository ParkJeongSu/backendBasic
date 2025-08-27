package kr.co.aim.api.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

@Getter
public class ChangeUsernameRequest {
    @NotBlank(message = "새로운 사용자 이름은 비워둘 수 없습니다.")
    private String newUsername;
}
