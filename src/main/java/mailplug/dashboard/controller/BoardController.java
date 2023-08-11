package mailplug.dashboard.controller;

import lombok.RequiredArgsConstructor;
import mailplug.dashboard.dto.request.BoardRequestDto;
import mailplug.dashboard.dto.response.ResponseDto;
import mailplug.dashboard.service.BoardService;
import org.springframework.web.bind.annotation.*;


@RestController
@RequiredArgsConstructor
public class BoardController {

    private final BoardService boardService;

    /**
     * 게시판 생성
     * @param boardRequestDto
     * @return
     */
    @PostMapping("/board/create")
    public ResponseDto<?> createBoard(@RequestBody BoardRequestDto boardRequestDto) {
        return boardService.createBoard(boardRequestDto);
    }

    /**
     * 게시판 조회
     * @return
     */
    @GetMapping("/board/find")
    public ResponseDto<?> findBoard() {
        return boardService.findBoard();
    }

    /**
     * 게시판 수정
     * @param boardId
     * @param boardRequestDto
     * @return
     */
    @PutMapping("/board/update/{boardId}")
    public ResponseDto<?> updateBoard(@PathVariable Long boardId, @RequestBody BoardRequestDto boardRequestDto) {
        return boardService.updateBoard(boardId, boardRequestDto);
    }

    /**
     * 게시판 삭제
     * @param boardId
     * @return
     */
    @DeleteMapping("board/delete/{boardId}")
    public ResponseDto<?> deleteBoard(@PathVariable Long boardId) {
        return boardService.deleteBoard(boardId);
    }


}
