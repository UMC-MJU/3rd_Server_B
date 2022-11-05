package umc.week6.domain.post.application;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import umc.week6.domain.hashtag.application.HashtagService;
import umc.week6.domain.member.domain.Member;
import umc.week6.domain.member.domain.MemberRepository;
import umc.week6.domain.post.domain.Post;
import umc.week6.domain.post.domain.PostRepository;
import umc.week6.domain.post.dto.UploadPostReq;
import umc.week6.global.DefaultAssert;
import umc.week6.global.payload.ApiResponse;
import umc.week6.global.payload.Message;

import java.net.URI;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class PostService {

    private final PostRepository postRepository;
    private final MemberRepository memberRepository;
    private final HashtagService hashtagService;

    @Transactional
    public ResponseEntity<?> uploadPost(UploadPostReq uploadPostReq) {
        Optional<Member> member = memberRepository.findByEmail(uploadPostReq.getEmail());
        DefaultAssert.isTrue(member.isPresent(), "존재하지 않는 이메일입니다.");

        Post post = Post.builder()
                .title(uploadPostReq.getTitle())
                .content(uploadPostReq.getContent())
                .anonymous(uploadPostReq.getAnonymous())
                .member(member.get())
                .build();

        postRepository.save(post);
        hashtagService.saveHashtag(uploadPostReq.getHashtags(), post);

        URI location = ServletUriComponentsBuilder
                .fromCurrentContextPath().path("/api/posts/{id}")
                .buildAndExpand(post.getId()).toUri();

        return ResponseEntity.created(location).body(
                ApiResponse.builder()
                        .check(true)
                        .information(Message.builder().message("게시글이 업로드 되었습니다.").build())
                        .build()
        );
    }



}
