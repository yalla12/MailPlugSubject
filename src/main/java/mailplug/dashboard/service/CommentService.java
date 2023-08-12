package mailplug.dashboard.service;

import lombok.RequiredArgsConstructor;
import mailplug.dashboard.domain.Comment;
import mailplug.dashboard.domain.Post;
import mailplug.dashboard.domain.Writer;
import mailplug.dashboard.dto.ErrorCode;
import mailplug.dashboard.dto.request.CommentUpdateRequestDto;
import mailplug.dashboard.dto.response.CommentResponseDto;
import mailplug.dashboard.dto.response.PageInfo;
import mailplug.dashboard.dto.response.ResponseDto;
import mailplug.dashboard.repository.CommentRepository;
import mailplug.dashboard.dto.request.CommentRequestDto;
import mailplug.dashboard.repository.PostRepository;
import mailplug.dashboard.repository.WriterRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;
    private final PostRepository postRepository;
    private final WriterRepository writerRepository;

    /**
     * 댓글 등록
     * @param postId
     * @param commentRequestDto
     * @return
     */
    @Transactional
    public ResponseDto<?> createComment(Long postId, CommentRequestDto commentRequestDto) {
        Post post = postRepository.findById(postId).orElse(null);
        if(post == null) return ResponseDto.fail(ErrorCode.NOT_FOUNT_POST);

        Writer writer = writerRepository.findByDisplayName(commentRequestDto.getDisplayName()).orElse(null);
        if(writer == null) return ResponseDto.fail(ErrorCode.NOT_FOUND_WRITER);

        Comment comment = new Comment(writer, post, commentRequestDto.getContents());
        commentRepository.save(comment);

        List<Comment> commentList = commentRepository.findAllByPost_PostId(postId);
        post.commentsCount(commentList.size());
        postRepository.save(post);

        return ResponseDto.success(comment);
    }

    /**
     * 댓글 목록 조회
     * @param offset
     * @param limit
     * @return
     */
    @Transactional(readOnly = true)
    public ResponseDto<?> findAllComment(int offset, int limit) {

        Pageable pageable = PageRequest.of(offset, limit);
        Page<Comment> commentPageList = commentRepository.findAll(pageable);

        List<CommentResponseDto> commentResponseDtos = new ArrayList<>();

        for(Comment comment : commentPageList) {
            CommentResponseDto commentResponseDto = new CommentResponseDto(comment.getCommentId(), comment.getWriter(), comment.getContents(), comment.getCreatedDateTime());
            commentResponseDtos.add(commentResponseDto);
        }

        PageInfo pageInfo = new PageInfo(commentResponseDtos.size(), offset, limit, commentResponseDtos.size());

        return ResponseDto.findSuccess(commentResponseDtos, pageInfo);
    }

    /**
     * 댓글 상세 조회
     * @param commentId
     * @return
     */
    @Transactional(readOnly = true)
    public ResponseDto<?> findComment(Long commentId) {
        Comment comment = commentRepository.findById(commentId).orElse(null);
        if(comment == null) return ResponseDto.fail(ErrorCode.NOT_FOUNT_COMMENT);

        CommentResponseDto commentResponseDto = new CommentResponseDto(comment.getCommentId(), comment.getWriter(), comment.getContents(), comment.getCreatedDateTime());

        return ResponseDto.success(commentResponseDto);
    }

    /**
     * 댓글 수정
     * @param commentId
     * @param commentUpdateRequestDto
     * @return
     */
    @Transactional
    public ResponseDto<?> updateComment(Long commentId, CommentUpdateRequestDto commentUpdateRequestDto) {
        Comment comment = commentRepository.findById(commentId).orElse(null);
        if(comment == null) return ResponseDto.fail(ErrorCode.NOT_FOUNT_COMMENT);

        comment.updateComment(commentUpdateRequestDto.getContents());
        commentRepository.save(comment);

        return ResponseDto.success(comment);
    }

    /**
     * 댓글 삭제
     * @param commentId
     * @return
     */
    @Transactional
    public ResponseDto<?> deleteComment(Long commentId) {
        Comment comment = commentRepository.findById(commentId).orElse(null);
        if(comment == null) return ResponseDto.fail(ErrorCode.NOT_FOUNT_COMMENT);

        commentRepository.delete(comment);

        Post post = postRepository.findById(comment.getPost().getPostId()).orElse(null);

        List<Comment> commentList = commentRepository.findAllByPost_PostId(post.getPostId());
        post.commentsCount(commentList.size());
        postRepository.save(post);

        return ResponseDto.success(comment);

    }






}
