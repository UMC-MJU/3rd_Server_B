package umc.week6.domain.member.presentation;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import umc.week6.domain.member.application.MemberService;
import umc.week6.domain.member.dto.GetMemberRes;
import umc.week6.global.error.dto.ErrorResponse;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/members")
public class MemberController {

    final Logger logger = LoggerFactory.getLogger(this.getClass());

    private final MemberService memberService;

    @Operation(summary = "ID로 유저 조회", description = "ID로 유저 조회")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "유저 조회 성공", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = GetMemberRes.class))}),
            @ApiResponse(responseCode = "400", description = "유저 조회 실패", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class))})
    })
    @GetMapping("/{id}")
    public ResponseEntity<?> getMemberById(
            @PathVariable Long id
    ) {
        return memberService.getMember(id);
    }

    @Operation(summary = "유저 전체 조회", description = "유저 전체 조회")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "유저 전체 조회 성공", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = GetMemberRes.class))}),
            @ApiResponse(responseCode = "400", description = "유저 전체 조회 실패", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class))})
    })
    @GetMapping("")
    public ResponseEntity<?> getMembers() {
        return memberService.getMembers();
    }
}
