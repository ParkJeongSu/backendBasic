package kr.co.aim.api.web.controller;

import jakarta.validation.Valid;
import kr.co.aim.domain.model.User;
import kr.co.aim.api.dto.request.ChangeUsernameRequest;
import kr.co.aim.api.dto.response.UserResponse;
import kr.co.aim.api.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService; // 👈 Domain 계층의 Service에 의존

    // 1. 요청 접수: PATCH /api/users/{userId}/username
    @PatchMapping("/{userId}/username")
    public ResponseEntity<UserResponse> changeUsername(
            @PathVariable Long userId,
            // 2. 요청 데이터 파싱 및 검증
            @Valid @RequestBody ChangeUsernameRequest requestDto
    ) {
        // 3. 서비스 계층에 작업 위임
        User updatedUser = userService.changeUsername(userId, requestDto.getNewUsername());

        // 4. 결과 변환 및 HTTP 응답
        UserResponse responseDto = new UserResponse(updatedUser.getId(), updatedUser.getUsername(), updatedUser.getEmail());
        return ResponseEntity.ok(responseDto);
    }
}
