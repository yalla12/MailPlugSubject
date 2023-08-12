package mailplug.dashboard.dto.response;

import lombok.Getter;
import lombok.NoArgsConstructor;
import mailplug.dashboard.domain.Writer;

import java.time.LocalDateTime;
import java.util.Date;

@Getter
@NoArgsConstructor
public class CommentResponseDto {

    private Long commentId;
    private Writer writer;
    private String contents;
    private LocalDateTime createdDateTime;


    public CommentResponseDto(Long commentId, Writer writer, String contents, LocalDateTime createdDateTime) {
        this.commentId = commentId;
        this.writer = writer;
        this.contents = contents;
        this.createdDateTime = createdDateTime;
    }
}

