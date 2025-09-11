package kr.co.aim.common.format.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Getter;

@Builder
@Schema(description = "사용자 정보 응답 DTO")
@Getter
public class ChangeUsernameRequest {

    @Schema(description = "변경할 사용자 이름", example = "홍길동")
    @NotBlank(message = "새로운 사용자 이름은 비워둘 수 없습니다.")
    private String newUsername;
}
