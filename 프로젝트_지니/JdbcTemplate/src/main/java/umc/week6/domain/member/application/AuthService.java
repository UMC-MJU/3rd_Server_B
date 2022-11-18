package umc.week6.domain.member.application;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import umc.week6.domain.member.domain.Member;
import umc.week6.domain.member.domain.MemberDao;
import umc.week6.domain.member.dto.GetMemberRes;
import umc.week6.domain.member.dto.SignInReq;
import umc.week6.domain.member.dto.SignUpReq;
import umc.week6.global.DefaultAssert;
import umc.week6.global.error.DefaultException;
import umc.week6.global.error.dto.ErrorCode;
import umc.week6.global.payload.ApiResponse;
import umc.week6.global.payload.Message;
import umc.week6.global.util.SHA256;

import static umc.week6.global.error.dto.ErrorCode.*;


@Service
@RequiredArgsConstructor
@Slf4j
public class AuthService {

    private final MemberDao memberDao;

    public ResponseEntity<?> signUp(SignUpReq signUpReq){
        DefaultAssert.isTrue(!memberDao.existsMemberByEmail(signUpReq.getEmail()), "이미 존재하는 이메일입니다.");
        String pwd;

        try {
            pwd = SHA256.encrypt(signUpReq.getPassword());
            signUpReq.setPassword(pwd);
        } catch (Exception e) {
            throw new DefaultException(INVALID_CHECK);
        }

        memberDao.createMember(signUpReq);
        return ResponseEntity.ok().body(ApiResponse.builder()
                .check(true)
                .information(Message.builder().message("회원가입에 성공했습니다.").build())
                .build());
    }

    public ResponseEntity<?> signIn(SignInReq signInReq) {
        Member member = memberDao.getPwd(signInReq);
        DefaultAssert.isObjectNull(member);
        String reqPassword;

        try {
            reqPassword = SHA256.encrypt(signInReq.getPassword());
        } catch (Exception ignored) {
            throw new DefaultException(INVALID_CHECK);
        }

        if (!member.getPassword().equals(reqPassword)) {
            throw new DefaultException(INVALID_AUTHENTICATION);
        }

        return ResponseEntity.ok().body(ApiResponse.builder()
                .check(true)
                .information(GetMemberRes.builder()
                        .id(member.getId())
                        .email(member.getEmail())
                        .nickname(member.getNickname())
                        .build())
                .build());
    }

}
