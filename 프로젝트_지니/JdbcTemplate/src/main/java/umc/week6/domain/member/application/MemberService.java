package umc.week6.domain.member.application;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import umc.week6.domain.member.domain.MemberDao;
import umc.week6.domain.member.dto.GetMemberRes;
import umc.week6.global.DefaultAssert;
import umc.week6.global.payload.ApiResponse;

import java.util.List;


@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberDao memberDao;

    public ResponseEntity<?> getMember(Long id) {
        GetMemberRes getMemberRes = memberDao.findMemberById(id);
        DefaultAssert.isObjectNull(getMemberRes);
        return ResponseEntity.ok().body(ApiResponse.builder()
                .check(true)
                .information(getMemberRes)
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
