package mailplug.dashboard.dto.response;

import lombok.Getter;
import lombok.NoArgsConstructor;
import mailplug.dashboard.domain.Board;

import java.util.List;

@Getter
@NoArgsConstructor
public class BoardResponseDto {

    private List<Board> boardList;
    
}
