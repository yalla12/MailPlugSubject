package mailplug.dashboard.service;

import lombok.RequiredArgsConstructor;
import mailplug.dashboard.domain.Board;
import mailplug.dashboard.dto.request.BoardRequestDto;
import mailplug.dashboard.dto.response.ResponseDto;
import mailplug.dashboard.repository.BoardRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class BoardService {

    private final BoardRepository boardRepository;

    /**
     *  게시판 생성
     * */
    @Transactional
    public ResponseDto<?> createBoard(BoardRequestDto boardRequestDto) {

        Board board = new Board(boardRequestDto.getDisplayName(), boardRequestDto.getDisplayName());
        boardRepository.save(board);

        return ResponseDto.success(board);
    }
}
