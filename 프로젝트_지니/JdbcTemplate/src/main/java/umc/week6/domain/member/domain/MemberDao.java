package umc.week6.domain.member.domain;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import umc.week6.domain.member.dto.GetMemberRes;
import umc.week6.domain.member.dto.SignInReq;
import umc.week6.domain.member.dto.SignUpReq;
import umc.week6.global.DefaultAssert;

import javax.sql.DataSource;
import java.util.List;


@Repository
public class MemberDao {

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public void setDataSource(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public int createMember(SignUpReq signUpReq) {
        String createMemberQuery = "INSERT INTO Member (email, password, nickname) VALUES(?, ?, ?)";
        Object[] createMemberParams = new Object[]{signUpReq.getEmail(), signUpReq.getPassword(), signUpReq.getNickname()};
        this.jdbcTemplate.update(createMemberQuery, createMemberParams);

        String lastInsertIdQuery = "SELECT last_insert_id()";
        return this.jdbcTemplate.queryForObject(lastInsertIdQuery, int.class);
    }

    public boolean existsMemberByEmail(String email) {
        String findEmailQuery = "SELECT exists(SELECT email from Member WHERE email = ?)";
        String findEmailParams = email;
        return this.jdbcTemplate.queryForObject(findEmailQuery, boolean.class, findEmailParams);
    }

    public Member findMemberById(Long id) {
        String query = "SELECT * FROM member WHERE member.id = ?";
        Long params = id;
        return this.jdbcTemplate.queryForObject(query,
                (rs, rowNum) -> new Member(
                        rs.getLong("id"),
                        rs.getString("email"),
                        rs.getString("passworc"),
                        rs.getString("nickname")),
                params);
    }

    public Member findMemberByEmail(String email) {
        String query = "SELECT * FROM member WHERE member.email = ?";
        Object[] params = new Object[]{email};
        return this.jdbcTemplate.queryForObject(query,
                (rs, rowNum) -> new Member(
                        rs.getLong("id"),
                        rs.getString("email"),
                        rs.getString("password"),
                        rs.getString("nickname")),
                params);
    }

    public List<GetMemberRes> findMembers() {
        String query = "SELECT * FROM member";
        List<GetMemberRes> result = this.jdbcTemplate.query(query,
                (rs, rowNum) -> new GetMemberRes(
                        rs.getLong("id"),
                        rs.getString("email"),
                        rs.getString("nickname"))
        );
        DefaultAssert.isObjectNull(result);
        return result;
    }

    public Member getPwd(SignInReq signInReq) {
        String query = "SELECT * FROM member WHERE email = ?";
        String param = signInReq.getEmail();

        Member member = this.jdbcTemplate.queryForObject(query,
                (rs, rowNum) -> new Member(
                        rs.getLong("id"),
                        rs.getString("email"),
                        rs.getString("password"),
                        rs.getString("nickname")
                ),
                param);
        DefaultAssert.isObjectNull(member);
        return member;
    }

}
