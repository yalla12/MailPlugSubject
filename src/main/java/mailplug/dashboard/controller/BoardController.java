package mailplug.dashboard.controller;

import lombok.RequiredArgsConstructor;
import mailplug.dashboard.dto.request.BoardRequestDto;
import mailplug.dashboard.dto.response.ResponseDto;
import mailplug.dashboard.service.BoardService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequiredArgsConstructor
public class BoardController {

    private final BoardService boardService;

    /**
     *  게시판 생성
     * */
    @PostMapping("/board")
    public ResponseDto<?> createBoard(@RequestBody BoardRequestDto boardRequestDto) {
        return boardService.createBoard(boardRequestDto);
    }
}
