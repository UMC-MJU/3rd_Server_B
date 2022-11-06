package umc.week6.domain.hashtag.domain;

import lombok.Getter;
import umc.week6.domain.post.domain.Post;

import java.util.List;
import java.util.stream.Collectors;

@Getter
public class Hashtags {

    private final List<Hashtag> value;

    public Hashtags(List<Hashtag> value) {
        this.value = value;
    }

    public static Hashtags of(List<PostHashtag> postHashtags) {
        List<Hashtag> hashtags = postHashtags.stream()
                .map(PostHashtag::getHashtag)
                .collect(Collectors.toList());
        return new Hashtags(hashtags);
    }

    public List<PostHashtag> getPostHashtags(Post post) {
        return value.stream()
                .map(hashtag -> PostHashtag.builder().post(post).hashtag(hashtag).build())
                .collect(Collectors.toList());
    }

}
