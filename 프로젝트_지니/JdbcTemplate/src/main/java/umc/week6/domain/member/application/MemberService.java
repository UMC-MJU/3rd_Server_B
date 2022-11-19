package umc.week6.domain.member.application;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import umc.week6.domain.member.domain.Member;
import umc.week6.domain.member.domain.MemberDao;
import umc.week6.domain.member.dto.GetMemberRes;
import umc.week6.global.DefaultAssert;
import umc.week6.global.payload.ApiResponse;

import java.util.List;


@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MemberService {

    private final MemberDao memberDao;

    public ResponseEntity<?> getMember(Long id) {
        Member member = memberDao.findMemberById(id);
        DefaultAssert.isObjectNull(member);
        return ResponseEntity.ok().body(ApiResponse.builder()
                .check(true)
                .information(member)
                .build());
    }

    public ResponseEntity<?> getMembers() {
        List<GetMemberRes> getMembersRes = memberDao.findMembers();
        DefaultAssert.isObjectNull(getMembersRes);
        return ResponseEntity.ok().body(ApiResponse.builder()
                .check(true)
                .information(getMembersRes)
                .build());
    }
}
