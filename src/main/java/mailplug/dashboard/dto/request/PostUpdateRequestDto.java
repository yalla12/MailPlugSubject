package mailplug.dashboard.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class PostUpdateRequestDto {

    private String title;
    private String contents;
}
