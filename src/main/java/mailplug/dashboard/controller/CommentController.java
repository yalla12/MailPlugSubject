package mailplug.dashboard.controller;

import lombok.RequiredArgsConstructor;

import mailplug.dashboard.dto.request.CommentUpdateRequestDto;
import mailplug.dashboard.dto.response.ResponseDto;
import mailplug.dashboard.dto.request.CommentRequestDto;
import mailplug.dashboard.service.CommentService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;

    /**
     * 댓글 등록
     * @param postId
     * @param commentRequestDto
     * @return
     */
    @PostMapping("/comment/create/{postId}")
    public ResponseDto<?> createComment(@PathVariable Long postId, @RequestBody CommentRequestDto commentRequestDto) {
        return commentService.createComment(postId, commentRequestDto);
    }

    /**
     * 댓글 목록 조회
     * @param offset
     * @param limit
     * @return
     */
    @GetMapping("/comment/findAll")
    public ResponseDto<?> findALlComment(@RequestParam int offset, @RequestParam int limit) {
        return commentService.findAllComment(offset, limit);
    }

    /**
     * 댓글 상세 조회
     * @param commentId
     * @return
     */
    @GetMapping("/comment/find/{commentId}")
    public ResponseDto<?> findComment(@PathVariable Long commentId) {
        return commentService.findComment(commentId);
    }

    /**
     * 댓글 수정
     * @param commentId
     * @param commentUpdateRequestDto
     * @return
     */
    @PutMapping("/comment/update/{commentId}")
    public ResponseDto<?> updateComment(@PathVariable Long commentId, @RequestBody CommentUpdateRequestDto commentUpdateRequestDto) {
        return  commentService.updateComment(commentId, commentUpdateRequestDto);
    }

    /**
     * 댓글 삭제
     * @param commentId
     * @return
     */
    @DeleteMapping("/comment/delete/{commentId}")
    public ResponseDto<?> deleteComment(@PathVariable Long commentId) {
        return commentService.deleteComment(commentId);
    }


}
