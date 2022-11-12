package umc.week6.domain.hashtag.application;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import umc.week6.domain.hashtag.domain.Hashtag;
import umc.week6.domain.hashtag.domain.Hashtags;
import umc.week6.domain.hashtag.domain.repository.HashtagRepository;
import umc.week6.domain.hashtag.domain.repository.PostHashtagRepository;
import umc.week6.domain.post.domain.Post;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class HashtagService {

    private final HashtagRepository hashtagRepository;
    private final PostHashtagRepository postHashtagRepository;

    @Transactional
    public void saveHashtag(List<String> names, Post savedPost) {
        Hashtags hashtags = new Hashtags(names.stream()
                .map(this::findOrSave)
                .collect(Collectors.toList()));
        postHashtagRepository.saveAll(hashtags.getPostHashtags(savedPost));
    }

    public Hashtags findHashtagsByPost(Post post) {
        return Hashtags.of(postHashtagRepository.findAllByPost(post));
    }

    public void deleteAllByPost(Hashtags hashtags, Post post) {
        postHashtagRepository.deleteAllByPost(post);
        deleteNoUsedHashtags(hashtags);
    }

    private Hashtag findOrSave(String name) {
        return hashtagRepository
                .findByName(name)
                .orElseGet(() -> hashtagRepository.save(Hashtag.builder().name(name).build()));
    }

    private void deleteNoUsedHashtags(Hashtags hashtags) {
        for (Hashtag hashtag : hashtags.getValue()) {
            if (!postHashtagRepository.existsByHashtagId(hashtag.getId())) {
                hashtagRepository.delete(hashtag);
            }
        }
    }
}
