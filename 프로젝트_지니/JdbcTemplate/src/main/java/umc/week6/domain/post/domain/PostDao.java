package umc.week6.domain.post.domain;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import umc.week6.domain.post.dto.UploadPostReq;

import javax.sql.DataSource;
import java.util.List;

@Repository
public class PostDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public void setDataSource(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public int createPost(UploadPostReq uploadPostReq) {
        String uploadPostQuery = "INSERT INTO `POST`(member_id, title, content) VALUES(?, ?, ?)";
        Object[] uploadPostParams = new Object[]{uploadPostReq.getUserId(), uploadPostReq.getTitle(), uploadPostReq.getContent()};
        this.jdbcTemplate.update(uploadPostQuery, uploadPostParams);

        String lastInsertIdQuery = "SELECT last_insert_id()";
        return this.jdbcTemplate.queryForObject(lastInsertIdQuery, int.class);
    }

}
