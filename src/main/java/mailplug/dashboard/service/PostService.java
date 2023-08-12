package mailplug.dashboard.service;

import lombok.RequiredArgsConstructor;
import mailplug.dashboard.domain.Board;
import mailplug.dashboard.domain.Post;
import mailplug.dashboard.domain.Writer;
import mailplug.dashboard.dto.ErrorCode;
import mailplug.dashboard.dto.request.PostRequestDto;
import mailplug.dashboard.dto.request.PostUpdateRequestDto;
import mailplug.dashboard.dto.response.PageInfo;
import mailplug.dashboard.dto.response.PostResponseDto;
import mailplug.dashboard.dto.response.ResponseDto;
import mailplug.dashboard.repository.BoardRepository;
import mailplug.dashboard.repository.PostRepository;
import mailplug.dashboard.repository.WriterRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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
        if(board == null) return ResponseDto.fail(ErrorCode.NOT_FOUND_BOARD);

        Writer writer = writerRepository.findByDisplayName(postRequestDto.getDisplayName()).orElse(null);
        if(writer == null) return ResponseDto.fail(ErrorCode.NOT_FOUND_WRITER);


        Post post = new Post(postRequestDto.getTitle(), board, writer, postRequestDto.getContents());
        postRepository.save(post);

        return ResponseDto.success(post);
    }

    /**
     * 게시글 목록 조회
     * @param offset
     * @param limit
     * @return
     */
    @Transactional(readOnly = true)
    public ResponseDto<?> findAllPost(int offset, int limit) {

        Pageable pageable = PageRequest.of(offset, limit);
        Page<Post> postPageList = postRepository.findAll(pageable) ;
        List<PostResponseDto> postResponseDtos = new ArrayList<>();

        for(Post post : postPageList) {
            PostResponseDto postResponseDto = new PostResponseDto(post.getPostId(),
                    post.getTitle(),
                    post.getBoard().getBoardId(),
                    post.getBoard().getDisplayName(),
                    post.getWriter(),
                    "",
                    post.getCreatedDateTime(),
                    post.getCommentsCount()
            );
            postResponseDtos.add(postResponseDto);
        }

        PageInfo pageInfo = new PageInfo(postResponseDtos.size(), offset, limit, postResponseDtos.size());


        return ResponseDto.findSuccess(postResponseDtos, pageInfo);
    }

    /**
     * 게시물 상세 조회
     * @param postId
     * @return
     */
    @Transactional(readOnly = true)
    public ResponseDto<?> findPost(Long postId) {
        Post post = postRepository.findById(postId).orElse(null);
        if(post == null) return ResponseDto.fail(ErrorCode.NOT_FOUNT_POST);

        PostResponseDto postResponseDto = new PostResponseDto(post.getPostId(),
                post.getTitle(),
                post.getBoard().getBoardId(),
                post.getBoard().getDisplayName(),
                post.getWriter(),
                post.getContents(),
                post.getCreatedDateTime(),
                post.getCommentsCount()
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
        if(post == null) return ResponseDto.fail(ErrorCode.NOT_FOUNT_POST);

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
        if(post == null) return ResponseDto.fail(ErrorCode.NOT_FOUNT_POST);

        postRepository.delete(post);

        return ResponseDto.success(post);
    }
}

