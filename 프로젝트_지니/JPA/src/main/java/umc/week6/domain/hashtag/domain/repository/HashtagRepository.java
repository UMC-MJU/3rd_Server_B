package umc.week6.domain.hashtag.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import umc.week6.domain.hashtag.domain.Hashtag;

import java.util.Optional;

public interface HashtagRepository extends JpaRepository<Hashtag, Long> {

    Optional<Hashtag> findByName(String name);
}
