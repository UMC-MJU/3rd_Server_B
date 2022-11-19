package umc.week6.domain.member.presentation;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import umc.week6.domain.member.application.AuthService;
import umc.week6.domain.member.dto.GetMemberRes;
import umc.week6.domain.member.dto.SignInReq;
import umc.week6.domain.member.dto.SignUpReq;
import umc.week6.global.error.dto.ErrorResponse;
import umc.week6.global.payload.Message;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthController {

    final Logger logger = LoggerFactory.getLogger(this.getClass());

    private final AuthService authService;


    @Operation(summary = "회원 가입", description = "회원 가입")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "회원 가입 성공", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = Message.class))}),
            @ApiResponse(responseCode = "400", description = "회원 가입 실패", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class))})
    })
    @PostMapping("/signup")
    public ResponseEntity<?> signUp(
            @Parameter(description = "SignUpReq", required = true) @Valid @RequestBody SignUpReq signUpReq
    ) {
        return authService.signUp(signUpReq);
    }

    @Operation(summary = "로그인", description = "로그인")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "로그인 성공", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = GetMemberRes.class))}),
            @ApiResponse(responseCode = "400", description = "로그인 실패", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class))})
    })
    @PostMapping("/signin")
    public ResponseEntity<?> signIn(
            @Parameter(description = "SignInReq", required = true) @Valid @RequestBody SignInReq signInReq
    ) {
        return authService.signIn(signInReq);
    }

}
