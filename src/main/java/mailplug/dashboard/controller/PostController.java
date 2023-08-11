package mailplug.dashboard.controller;

import lombok.RequiredArgsConstructor;
import mailplug.dashboard.dto.request.PostRequestDto;
import mailplug.dashboard.dto.response.ResponseDto;
import mailplug.dashboard.service.PostService;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;

    @PostMapping("/post/create/{boardId}")
    public ResponseDto<?> createPost(@PathVariable Long boardId, @RequestBody PostRequestDto postRequestDto) {
        return postService.createPost(boardId, postRequestDto);
    }
}

