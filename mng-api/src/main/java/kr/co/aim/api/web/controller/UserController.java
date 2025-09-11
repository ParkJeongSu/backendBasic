package kr.co.aim.api.web.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import kr.co.aim.domain.model.User;
import kr.co.aim.common.format.request.ChangeUsernameRequest;
import kr.co.aim.common.format.response.UserResponse;
import kr.co.aim.api.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Tag(name = "User", description = "사용자 관련 API")
@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService; // 👈 Domain 계층의 Service에 의존

    @Operation(summary = "특정 사용자 정보 조회", description = "사용자 ID를 이용하여 특정 사용자의 정보를 조회합니다.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "조회 성공",
                    content = @Content(schema = @Schema(implementation = UserResponse.class))),
            @ApiResponse(responseCode = "404", description = "사용자를 찾을 수 없음")
    })
    // 1. 요청 접수: PATCH /api/users/{userId}/username
    @PatchMapping("/{userId}/username")
    public ResponseEntity<UserResponse> changeUsername(
            @Parameter(description = "변경할 사용자의 ID", required = true, example = "1")
            @PathVariable Long userId,
            // 2. 요청 데이터 파싱 및 검증
            @Parameter(description = "변경할 사용자의 이름", required = true, example = "1")
            @Valid @RequestBody ChangeUsernameRequest requestDto
    ) {
        // 3. 서비스 계층에 작업 위임
        User updatedUser = userService.changeUsername(userId, requestDto.getNewUsername());

        // 4. 결과 변환 및 HTTP 응답
        UserResponse responseDto = new UserResponse(updatedUser.getId(), updatedUser.getUsername(), updatedUser.getEmail());
        return ResponseEntity.ok(responseDto);
    }
}
