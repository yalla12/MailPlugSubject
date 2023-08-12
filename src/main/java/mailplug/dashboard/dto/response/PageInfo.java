package mailplug.dashboard.dto.response;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class PageInfo {
    private int count;
    private int offset;
    private int limit;
    private int total;

    public PageInfo(int count, int offset, int limit, int total) {
        this.count = count;
        this.offset = offset;
        this.limit = limit;
        this.total = total;
    }
}
