package mailplug.dashboard.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Board {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long boardId;

    @Column(nullable = false)
    private String displayName;

    @Column(nullable = false)
    private String boardType;

    @Column
    private boolean isFavorite;

    @Column
    private int orderNo;

    public Board(String displayName, String boardType, int orderNo, boolean isFavorite) {
        this.displayName = displayName;
        this.boardType = boardType;
        this.orderNo = orderNo;
        this.isFavorite = isFavorite;
    }

    public void updateBoard(String displayName, String boardType, int orderNo) {
        this.displayName = displayName;
        this.boardType = boardType;
        this.orderNo = orderNo;
    }
}
