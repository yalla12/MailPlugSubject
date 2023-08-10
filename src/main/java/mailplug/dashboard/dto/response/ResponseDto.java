package mailplug.dashboard.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import mailplug.dashboard.dto.ErrorCode;


@Getter
@AllArgsConstructor
public class ResponseDto<T> { // 웹에서 Response 항목에 들어가는 틀을 만들어서 이런것들을 표현시켜줌
    private boolean success;
    private T data;
    private ErrorResponse errorResponse;


    public static <T> ResponseDto<T> success(T data) {
        return new ResponseDto<>(true, data, null);
    }

    public static <T> ResponseDto<T> fail(ErrorCode errorCode) {
        return new ResponseDto<>(false, null, new ErrorResponse(errorCode));
    }

}
