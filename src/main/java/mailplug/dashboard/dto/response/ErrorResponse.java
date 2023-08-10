package mailplug.dashboard.dto.response;


import lombok.Getter;
import lombok.Setter;
import mailplug.dashboard.dto.ErrorCode;

@Getter
@Setter
public class ErrorResponse {
    private int status;
    private String message;
    private String code;

    public ErrorResponse(ErrorCode errorCode){
        this.status = errorCode.getStatus();
        this.message = errorCode.getMessage();
        this.code = errorCode.getErrorCode();
    }
}
