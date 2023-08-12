package mailplug.dashboard.dto.response;

import lombok.Getter;
import lombok.NoArgsConstructor;
import mailplug.dashboard.domain.Board;

import java.util.List;

@Getter
@NoArgsConstructor
public class BoardResponseDto {

    private Long boardId;
    private String displayName;
    private boolean isFavorite;
    private int orderNo;

    public BoardResponseDto(Long boardId, String displayName, boolean isFavorite, int orderNo) {
        this.boardId = boardId;
        this.displayName = displayName;
        this.isFavorite = isFavorite;
        this.orderNo = orderNo;
    }

}
