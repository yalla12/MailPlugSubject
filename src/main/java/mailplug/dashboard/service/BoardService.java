package mailplug.dashboard.service;

import lombok.RequiredArgsConstructor;
import mailplug.dashboard.domain.Board;
import mailplug.dashboard.dto.ErrorCode;
import mailplug.dashboard.dto.request.BoardRequestDto;
import mailplug.dashboard.dto.response.BoardResponseDto;
import mailplug.dashboard.dto.response.PageInfo;
import mailplug.dashboard.dto.response.ResponseDto;
import mailplug.dashboard.repository.BoardRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BoardService {

    private final BoardRepository boardRepository;

    /**
     * 게시판 생성
     * @param boardRequestDto
     * @return
     */
    @Transactional
    public ResponseDto<?> createBoard(BoardRequestDto boardRequestDto) {

        if(boardRequestDto.getIsFavorite() == null) boardRequestDto.isFavoriteNull();

        Board board = new Board(boardRequestDto.getDisplayName(), boardRequestDto.getBoardType(), boardRequestDto.getOrderNo(), boardRequestDto.getIsFavorite());
        boardRepository.save(board);

        return ResponseDto.success(board);
    }

    /**
     * 게시판 조회
     * @param offset
     * @param limit
     * @return
     */
    @Transactional(readOnly = true)
    public ResponseDto<?> findBoard(int offset, int limit) {

        Pageable pageable = PageRequest.of(offset,limit);
        Page<Board> boardPageList = boardRepository.findAll(pageable);

        List<BoardResponseDto> boardResponseDtos = new ArrayList<>();

        for(Board board : boardPageList) {
            BoardResponseDto boardResponseDto = new BoardResponseDto(board.getBoardId(), board.getDisplayName(), board.isFavorite(), board.getOrderNo());
            boardResponseDtos.add(boardResponseDto);
        }

        PageInfo pageInfo = new PageInfo(boardResponseDtos.size(), offset, limit, boardResponseDtos.size());

        return ResponseDto.findSuccess(boardResponseDtos, pageInfo);

    }

    /**
     * 게시판 수정
     * @param boardId
     * @param boardRequestDto
     * @return
     */
    @Transactional
    public ResponseDto<?> updateBoard(Long boardId, BoardRequestDto boardRequestDto) {
        Board board = boardRepository.findById(boardId).orElse(null);
        if(board == null) return ResponseDto.fail(ErrorCode.NOT_FOUND_BOARD);

        board.updateBoard(boardRequestDto.getDisplayName(), boardRequestDto.getBoardType(), boardRequestDto.getOrderNo());
        boardRepository.save(board);

        return ResponseDto.success(board);
    }

    /**
     * 게시판 삭제
     * @param boardId
     * @return
     */
    @Transactional
    public ResponseDto<?> deleteBoard(Long boardId) {
        Board board = boardRepository.findById(boardId).orElse(null);
        if(board == null) return ResponseDto.fail(ErrorCode.NOT_FOUND_BOARD);

        boardRepository.delete(board);

        return ResponseDto.success(board);
    }
 }


