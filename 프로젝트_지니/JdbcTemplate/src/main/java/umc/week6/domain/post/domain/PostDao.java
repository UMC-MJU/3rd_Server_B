package umc.week6.domain.post.domain;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import umc.week6.domain.post.dto.UpdatePostReq;
import umc.week6.domain.post.dto.UploadPostReq;

import javax.sql.DataSource;

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

    public Post updatePost(UpdatePostReq updatePostReq, Long id) {
        String updatePostQuery = "UPDATE Post SET title=?, content=? WHERE id=?";
        Object[] updatePostParams = new Object[]{updatePostReq.getTitle(), updatePostReq.getContent(), id};
        this.jdbcTemplate.update(updatePostQuery, updatePostParams);

        String lastInsertPostQuery = "SELECT * FROM Post WHERE id=?";
        Object[] lastInsertIdParams = new Object[]{id};
        return this.jdbcTemplate.queryForObject(lastInsertPostQuery,
                (rs, rowNum) -> new Post(
                        rs.getLong("id"),
                        rs.getLong("member_id"),
                        rs.getString("title"),
                        rs.getString("content")),
                lastInsertIdParams);
    }

    public Post findPostById(Long id) {
        String findPostByIdQuery = "SELECT * FROM Post WHERE id=?";
        Object[] findPostByIdParams = new Object[]{id};

        return this.jdbcTemplate.queryForObject(findPostByIdQuery,
                (rs, rowNum) -> new Post(
                        rs.getLong("id"),
                        rs.getLong("member_id"),
                        rs.getString("title"),
                        rs.getString("content")),
                findPostByIdParams);
    }

    public void deletePostById(Long id) {
        String deletePostByIdQuery = "DELETE FROM Post WHERE id=?";
        Object[] deletePostByIdParams = new Object[]{id};

        this.jdbcTemplate.update(deletePostByIdQuery, deletePostByIdParams);
    }

}
