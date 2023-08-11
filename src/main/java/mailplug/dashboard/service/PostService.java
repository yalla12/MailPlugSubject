package mailplug.dashboard.service;

import lombok.RequiredArgsConstructor;
import mailplug.dashboard.domain.Board;
import mailplug.dashboard.domain.Post;
import mailplug.dashboard.domain.Writer;
import mailplug.dashboard.dto.ErrorCode;
import mailplug.dashboard.dto.request.BoardRequestDto;
import mailplug.dashboard.dto.request.PostRequestDto;
import mailplug.dashboard.dto.request.PostUpdateRequestDto;
import mailplug.dashboard.dto.response.PostResponseDto;
import mailplug.dashboard.dto.response.ResponseDto;
import mailplug.dashboard.repository.BoardRepository;
import mailplug.dashboard.repository.PostRepository;
import mailplug.dashboard.repository.WriterRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;
    private final BoardRepository boardRepository;
    private final WriterRepository writerRepository;

    /**
     * 게시글 등록
     * @param boardId
     * @param postRequestDto
     * @return
     */
    public ResponseDto<?> createPost(Long boardId, PostRequestDto postRequestDto) {
        Board board = boardRepository.findById(boardId).orElse(null);
        if(board == null) return ResponseDto.fail(ErrorCode.NOT_FOUND);

        Writer writer = writerRepository.findByDisplayName(postRequestDto.getDisplayName()).orElse(null);
        if(writer == null) return ResponseDto.fail(ErrorCode.NOT_FOUND);


        Post post = new Post(postRequestDto.getTitle(), board, writer, postRequestDto.getContents());
        postRepository.save(post);

        return ResponseDto.success(post);
    }

    /**
     * 게시글 목록 조회
     * @return
     */
    public ResponseDto<?> findAllPost() {
        List<Post> postList = postRepository.findAll();

        List<PostResponseDto> postResponseDtos = new ArrayList<>();

        for(Post post : postList) {
            PostResponseDto postResponseDto = new PostResponseDto(post.getPostId(),
                    post.getTitle(),
                    post.getBoard().getBoardId(),
                    post.getBoard().getDisplayName(),
                    post.getWriter(),
                    "",
                    null,
                    0
            );
            postResponseDtos.add(postResponseDto);
        }

        return ResponseDto.success(postResponseDtos);
    }

    /**
     * 게시물 상세 조회
     * @param postId
     * @return
     */
    @Transactional(readOnly = true)
    public ResponseDto<?> findPost(Long postId) {
        Post post = postRepository.findById(postId).orElse(null);
        if(post == null) return ResponseDto.fail(ErrorCode.NOT_FOUND);

        PostResponseDto postResponseDto = new PostResponseDto(post.getPostId(),
                post.getTitle(),
                post.getBoard().getBoardId(),
                post.getBoard().getDisplayName(),
                post.getWriter(),
                post.getContents(),
                null,
                0
        );

        return ResponseDto.success(postResponseDto);
    }

    /**
     * 게시글 수정
     * @param postId
     * @param postUpdateRequestDto
     * @return
     */
    @Transactional
    public ResponseDto<?> updatePost(Long postId, PostUpdateRequestDto postUpdateRequestDto) {
        Post post = postRepository.findById(postId).orElse(null);
        if(post == null) return ResponseDto.fail(ErrorCode.NOT_FOUND);

        post.updatePost(postUpdateRequestDto.getTitle(), postUpdateRequestDto.getContents());
        postRepository.save(post);

        return ResponseDto.success(post);
    }

    /**
     * 게시글 삭제
     * @param postId
     * @return
     */
    @Transactional
    public ResponseDto<?> deletePost(Long postId) {
        Post post = postRepository.findById(postId).orElse(null);
        if(post == null) return ResponseDto.fail(ErrorCode.NOT_FOUND);

        postRepository.delete(post);

        return ResponseDto.success(post);
    }
}



/*
H2 디비 테스트용
Insert Into WRITER(WRITER_ID, DISPLAY_NAME)  values(1, '홍길동')
 */