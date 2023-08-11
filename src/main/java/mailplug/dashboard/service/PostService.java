package mailplug.dashboard.service;

import lombok.RequiredArgsConstructor;
import mailplug.dashboard.domain.Board;
import mailplug.dashboard.domain.Post;
import mailplug.dashboard.domain.Writer;
import mailplug.dashboard.dto.ErrorCode;
import mailplug.dashboard.dto.request.PostRequestDto;
import mailplug.dashboard.dto.response.ResponseDto;
import mailplug.dashboard.repository.BoardRepository;
import mailplug.dashboard.repository.PostRepository;
import mailplug.dashboard.repository.WriterRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;
    private final BoardRepository boardRepository;
    private final WriterRepository writerRepository;

    public ResponseDto<?> createPost(Long boardId, PostRequestDto postRequestDto) {
        Board board = boardRepository.findById(boardId).orElse(null);
        if(board == null) return ResponseDto.fail(ErrorCode.NOT_FOUND);

        Writer writer = writerRepository.findByDisplayName(postRequestDto.getDisplayName()).orElse(null);
        if(writer == null) return ResponseDto.fail(ErrorCode.NOT_FOUND);


        Post post = new Post(postRequestDto.getTitle(), board, writer, postRequestDto.getContents());
        postRepository.save(post);

        return ResponseDto.success(post);
    }
}

/*
H2 디비 테스트용
Insert Into WRITER(WRITER_ID, DISPLAY_NAME)WRITER  values(1, '홍길동')
 */