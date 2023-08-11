package mailplug.dashboard.controller;

import lombok.RequiredArgsConstructor;

import mailplug.dashboard.dto.response.ResponseDto;
import mailplug.dashboard.dto.request.CommentRequestDto;
import mailplug.dashboard.service.CommentService;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

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


}
