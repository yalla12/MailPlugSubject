package mailplug.dashboard.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ErrorCode {

    NOT_FOUND_WRITER(500, "WRITER-ERR-500", "글쓴이를 찾을 수 없습니다."),
    NOT_FOUND_BOARD(500, "BOARD-ERR-500", "게시판을 찾을 수 없습니다."),
    NOT_FOUNT_POST(500, "POST-ERR-500", "게시글을 찾을 수 없습니다."),
    NOT_FOUNT_COMMENT(500, "COMMENT-ERR-500", "댓글을 찾을 수 없습니다.");



    private final int status;
    private final String errorCode;
    private final String message;
    }
