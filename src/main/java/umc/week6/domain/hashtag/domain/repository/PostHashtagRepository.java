package umc.week6.domain.hashtag.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import umc.week6.domain.hashtag.domain.PostHashtag;

public interface PostHashtagRepository extends JpaRepository<PostHashtag, Long> {
}
