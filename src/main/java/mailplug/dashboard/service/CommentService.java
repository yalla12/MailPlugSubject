package mailplug.dashboard.service;

import lombok.RequiredArgsConstructor;
import mailplug.dashboard.domain.Comment;
import mailplug.dashboard.domain.Post;
import mailplug.dashboard.domain.Writer;
import mailplug.dashboard.dto.ErrorCode;
import mailplug.dashboard.dto.response.ResponseDto;
import mailplug.dashboard.repository.CommentRepository;
import mailplug.dashboard.dto.request.CommentRequestDto;
import mailplug.dashboard.repository.PostRepository;
import mailplug.dashboard.repository.WriterRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;
    private final PostRepository postRepository;
    private final WriterRepository writerRepository;

    /**
     * 댓글 등록
     * @param postId
     * @param commentRequestDto
     * @return
     */
    @Transactional
    public ResponseDto<?> createComment(Long postId, CommentRequestDto commentRequestDto) {
        Post post = postRepository.findById(postId).orElse(null);
        if(post == null) return ResponseDto.fail(ErrorCode.NOT_FOUND);

        Writer writer = writerRepository.findByDisplayName(commentRequestDto.getDisplayName()).orElse(null);
        if(writer == null) return ResponseDto.fail(ErrorCode.NOT_FOUND);

        Comment comment = new Comment(writer, post, commentRequestDto.getContents());
        commentRepository.save(comment);

        return ResponseDto.success(comment);
    }

}
