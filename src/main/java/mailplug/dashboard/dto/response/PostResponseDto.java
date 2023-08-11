package mailplug.dashboard.dto.response;

import lombok.Getter;
import lombok.NoArgsConstructor;
import mailplug.dashboard.domain.Writer;

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
   private Date createdDateTime;
   private int commentsCount;
}


/*
"value": [
        {
            "postId": 1593171,
            "title": "test",
            "boardId": 28478,
            "boardDisplayName": "테스트 게시판",
            "writer": {
                "displayName": "홍길동"
            },
            "contents": "",
            "createdDateTime": "2023-06-30T06:15:08Z",
            "commentsCount": 0
        }
    ],
 */
