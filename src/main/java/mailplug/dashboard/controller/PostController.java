package mailplug.dashboard.controller;

import lombok.RequiredArgsConstructor;
import mailplug.dashboard.dto.request.BoardRequestDto;
import mailplug.dashboard.dto.request.PostRequestDto;
import mailplug.dashboard.dto.request.PostUpdateRequestDto;
import mailplug.dashboard.dto.response.ResponseDto;
import mailplug.dashboard.service.PostService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;

    /**
     * 게시글 생성
     * @param boardId
     * @param postRequestDto
     * @return
     */
    @PostMapping("/post/create/{boardId}")
    public ResponseDto<?> createPost(@PathVariable Long boardId, @RequestBody PostRequestDto postRequestDto) {
        return postService.createPost(boardId, postRequestDto);
    }

    /**
     * 게시글 목록 조회
     * @return
     */
    @GetMapping("/post/findAll")
    public ResponseDto<?> findAllPost() {
        return postService.findAllPost();
    }

    /**
     * 게시물 상세 조회
     * @param postId
     * @return
     */
    @GetMapping("/post/find/{postId}")
    public ResponseDto<?> findPost(@PathVariable Long postId) {
        return postService.findPost(postId);
    }

    /**
     * 게시글 수정
     * @param postId
     * @param postUpdateRequestDto
     * @return
     */
    @PutMapping("/post/update/{postId}")
    public ResponseDto<?> updatePost(@PathVariable Long postId, @RequestBody PostUpdateRequestDto postUpdateRequestDto) {
        return postService.updatePost(postId, postUpdateRequestDto);
    }

    /**
     * 게시글 삭제
     * @param postId
     * @return
     */
    @DeleteMapping("/post/delete/{postId}")
    public ResponseDto<?> deletePost(@PathVariable Long postId) {
        return postService.deletePost(postId);
    }


}

