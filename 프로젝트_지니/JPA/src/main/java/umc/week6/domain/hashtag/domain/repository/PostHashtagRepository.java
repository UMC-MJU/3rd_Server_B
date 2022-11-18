package umc.week6.domain.hashtag.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import umc.week6.domain.hashtag.domain.PostHashtag;
import umc.week6.domain.post.domain.Post;

import java.util.List;

public interface PostHashtagRepository extends JpaRepository<PostHashtag, Long> {

    List<PostHashtag> findAllByPostId(Long id);

    List<PostHashtag> findAllByPost(Post post);

    void deleteAllByPost(Post post);

    boolean existsByHashtagId(Long id);
}
