package mailplug.dashboard.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ErrorCode {
    NOT_FOUND(404,"COMMON-ERR-404","PAGE NOT FOUND"),
    INTER_SERVER_ERROR(500,"COMMON-ERR-500","INTER SERVER ERROR"),
    NOT_FOUND_MEMBER(500, "MEMBER-ERR-500", "유저를 찾을 수 없습니다."),
    NOT_FOUNT_POST(500, "POST-ERR-500", "게시물을 찾을 수 없습니다.");


    private final int status;
    private final String errorCode;
    private final String message;
    }
