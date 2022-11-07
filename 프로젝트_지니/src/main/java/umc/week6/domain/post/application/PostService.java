package umc.week6.domain.post.application;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import umc.week6.domain.comment.application.CommentService;
import umc.week6.domain.comment.domain.Comment;
import umc.week6.domain.comment.domain.CommentRepository;
import umc.week6.domain.hashtag.application.HashtagService;
import umc.week6.domain.hashtag.domain.Hashtag;
import umc.week6.domain.hashtag.domain.Hashtags;
import umc.week6.domain.member.application.MemberService;
import umc.week6.domain.member.domain.Member;
import umc.week6.domain.member.domain.MemberRepository;
import umc.week6.domain.post.domain.Post;
import umc.week6.domain.post.domain.PostRepository;
import umc.week6.domain.post.dto.PostDetailRes;
import umc.week6.domain.post.dto.UpdatePostReq;
import umc.week6.domain.post.dto.UploadPostReq;
import umc.week6.global.DefaultAssert;
import umc.week6.global.payload.ApiResponse;
import umc.week6.global.payload.Message;

import java.net.URI;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class PostService {

    private final PostRepository postRepository;
    private final CommentRepository commentRepository;

    private final HashtagService hashtagService;
    private final MemberService memberService;
    private final CommentService commentService;

    @Transactional
    public ResponseEntity<?> uploadPost(UploadPostReq uploadPostReq) {
        Member member = memberService.findMemberByEmail(uploadPostReq.getEmail());

        Post post = Post.builder()
                .title(uploadPostReq.getTitle())
                .content(uploadPostReq.getContent())
                .anonymous(uploadPostReq.isAnonymous())
                .member(member)
                .build();

        postRepository.save(post);
        hashtagService.saveHashtag(uploadPostReq.getHashtags(), post);

        URI location = ServletUriComponentsBuilder
                .fromCurrentContextPath().path("/api/posts/{id}")
                .buildAndExpand(post.getId()).toUri();

        return voidResponse("게시글이 업로드 되었습니다.", location);
    }

    public ResponseEntity<?> getPost(Long id) {
        Post post = findPostById(id);
        Hashtags hashtags = hashtagService.findHashtagsByPost(post);
        List<Comment> comments = commentRepository.findAllByPostId(post.getId());

        ApiResponse apiResponse = ApiResponse.builder()
                .check(true)
                .information(PostDetailRes.builder()
                        .title(post.getTitle())
                        .content(post.getContent())
                        .anonymous(post.isAnonymous())
                        .comments(commentService.findComments(post.getId()))
                        .hashtags(hashtags.getValue().stream()
                                .map(Hashtag::getName)
                                .collect(Collectors.toList()))
                        .build()).build();

        return ResponseEntity.ok().body(apiResponse);
    }

    @Transactional
    public ResponseEntity<?> updatePost(UpdatePostReq updatePostReq, Long postId) {
        Post post = findPostById(postId);
        Hashtags hashtags = hashtagService.findHashtagsByPost(post);

        post.updateTitle(updatePostReq.getTitle());
        post.updateContent(updatePostReq.getContent());
        post.updateAnonymous(updatePostReq.isAnonymous());

        hashtagService.deleteAllByPost(hashtags, post);
        hashtagService.saveHashtag(updatePostReq.getHashtags(), post);

        URI location = ServletUriComponentsBuilder
                .fromCurrentContextPath().path("/api/posts/{id}")
                .buildAndExpand(post.getId()).toUri();

        return voidResponse("게시글이 수정되었습니다.", location);
    }

    public Post findPostById(Long postId) {
        Optional<Post> post = postRepository.findById(postId);
        DefaultAssert.isTrue(post.isPresent(), "게시글이 존재하지 않습니다");
        return post.get();
    }

    private ResponseEntity<?> voidResponse(String message, URI uri) {
        return ResponseEntity.created(uri).body(
                ApiResponse.builder()
                        .check(true)
                        .information(Message.builder().message(message).build())
                        .build()
        );
    }

}
