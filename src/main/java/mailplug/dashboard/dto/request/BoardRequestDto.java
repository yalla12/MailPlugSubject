package mailplug.dashboard.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class BoardRequestDto {

    private String displayName;
    private String boardType;
    private int orderNo;
    private Boolean isFavorite;

    public void isFavoriteNull() {
        this.isFavorite = false;
    }

}
