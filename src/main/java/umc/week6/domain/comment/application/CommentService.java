package umc.week6.domain.comment.application;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import umc.week6.domain.comment.domain.Comment;
import umc.week6.domain.comment.domain.CommentRepository;
import umc.week6.domain.comment.dto.AddCommentReq;
import umc.week6.domain.comment.dto.CommentRes;
import umc.week6.domain.member.application.MemberService;
import umc.week6.domain.member.domain.Member;
import umc.week6.domain.member.domain.MemberRepository;
import umc.week6.domain.post.application.PostService;
import umc.week6.domain.post.domain.Post;
import umc.week6.domain.post.domain.PostRepository;
import umc.week6.global.error.DefaultException;
import umc.week6.global.error.dto.ErrorCode;
import umc.week6.global.payload.ApiResponse;
import umc.week6.global.payload.Message;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class CommentService {

    private final PostRepository postRepository;
    private final CommentRepository commentRepository;
    private final MemberRepository memberRepository;

    @Transactional
    public ResponseEntity<?> addComment(Long id, AddCommentReq addCommentReq) {
        Post post = postRepository.findById(id)
                .orElseThrow(() -> new DefaultException(ErrorCode.INVALID_CHECK, "게시글이 존재하지 않습니다."));
        Member member = memberRepository.findByEmail(addCommentReq.getEmail())
                .orElseThrow(() -> new DefaultException(ErrorCode.INVALID_CHECK, "유저가 존재하지 않습니다."));

        Comment comment = Comment.builder()
                .content(addCommentReq.getContent())
                .post(post)
                .member(member)
                .anonymous(addCommentReq.isAnonymous())
                .build();

        commentRepository.save(comment);

        URI location = ServletUriComponentsBuilder
                .fromCurrentContextPath().path("/api/posts/{id}")
                .buildAndExpand(post.getId()).toUri();

        return voidResponse("댓글이 등록되었습니다", location);
    }

    public List<CommentRes> findComments(Long postId) {
        List<Comment> comments = commentRepository.findAllByPostId(postId);
        return comments.stream()
                .map(this::convertToCommentRes)
                .collect(Collectors.toList());
    }

    private CommentRes convertToCommentRes(Comment comment) {
        Member member = comment.getMember();
        return CommentRes.builder()
                .id(comment.getId())
                .name(member.getName())
                .content(comment.getContent())
                .anonymous(comment.isAnonymous())
                .createdAt(comment.getCreatedAt())
                .build();
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
