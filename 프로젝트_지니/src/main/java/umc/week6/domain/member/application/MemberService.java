package umc.week6.domain.member.application;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import umc.week6.domain.member.domain.Member;
import umc.week6.domain.member.domain.MemberRepository;
import umc.week6.domain.member.dto.SignUpReq;
import umc.week6.global.DefaultAssert;
import umc.week6.global.payload.ApiResponse;
import umc.week6.global.payload.Message;

import java.net.URI;
import java.util.Optional;


@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MemberService {

    private final MemberRepository memberRepository;

    @Transactional
    public ResponseEntity<?> signUp(SignUpReq signUpReq) {
        DefaultAssert.isTrue(memberRepository.findByEmail(signUpReq.getEmail()).isEmpty(), "이미 존재하는 이메일 입니다.");

        Member member = Member.builder()
                .email(signUpReq.getEmail())
                .name(signUpReq.getName())
                .password(signUpReq.getPassword())
                .build();

        memberRepository.save(member);

        URI location = ServletUriComponentsBuilder
                .fromCurrentContextPath().path("/api/users/{id}")
                .buildAndExpand(member.getId()).toUri();

        ApiResponse apiResponse = ApiResponse.builder()
                .check(true)
                .information(Message.builder().message("회원가입에 성공했습니다.").build())
                .build();

        return ResponseEntity.created(location).body(apiResponse);

    }

    public Member findMemberByEmail(String email) {
        Optional<Member> member = memberRepository.findByEmail(email);
        DefaultAssert.isTrue(member.isPresent(), "존재하지 않는 이메일입니다.");
        return member.get();
    }

}
