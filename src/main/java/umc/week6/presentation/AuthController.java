package umc.week6.presentation;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import umc.week6.domain.member.application.MemberService;
import umc.week6.domain.member.dto.SignUpReq;
import umc.week6.global.error.dto.ErrorResponse;

import javax.validation.Valid;

@Tag(name = "Authorization", description = "Authorization API")
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final MemberService memberService;

    @Operation(summary = "회원 가입", description = "회원 가입")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "회원 가입 성공", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = umc.week6.global.payload.ApiResponse.class))}),
            @ApiResponse(responseCode = "400", description = "회원 가입 실패", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class))})
    })
    @PostMapping("/signup")
    public ResponseEntity<?> signUp(
            @Parameter(description = "SignUpReq", required = true) @Valid @RequestBody SignUpReq signUpReq
    ) {
        return memberService.signUp(signUpReq);
    }
}
