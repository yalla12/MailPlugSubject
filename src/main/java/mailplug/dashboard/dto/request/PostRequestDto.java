package mailplug.dashboard.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import mailplug.dashboard.domain.Writer;

@Getter
@NoArgsConstructor
public class PostRequestDto {

    private String title;
    private String displayName;
    private String contents;


}
