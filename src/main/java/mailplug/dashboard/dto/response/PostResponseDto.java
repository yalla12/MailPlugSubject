package mailplug.dashboard.dto.response;

import lombok.Getter;
import lombok.NoArgsConstructor;
import mailplug.dashboard.domain.Writer;

import java.time.LocalDateTime;
import java.util.Date;

@Getter
@NoArgsConstructor
public class PostResponseDto {
   private Long postId;
   private String title;
   private Long boardId;
   private String boardDisplayName;
   private Writer writer;
   private String contents;
   private LocalDateTime createdDateTime;
   private int commentsCount;


   public PostResponseDto(Long postId, String title, Long boardId, String boardDisplayName, Writer writer, String contents, LocalDateTime createdDateTime,  int commentsCount) {
      this.postId = postId;
      this.title = title;
      this.boardId = boardId;
      this.boardDisplayName = boardDisplayName;
      this.writer = writer;
      this.contents = contents;
      this.createdDateTime = createdDateTime;
      this.commentsCount = commentsCount;
   }
}

