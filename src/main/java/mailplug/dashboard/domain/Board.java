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

    @Column(nullable = false)
    private boolean isFavorite;

    @Column(nullable = false)
    private int orderNo;

    public Board(String displayName, String boardType) {
        this.displayName = displayName;
        this. boardType = boardType;
        this.isFavorite = false;
    }

    public void updateBoard(String displayName, String boardType) {
        this.displayName = displayName;
        this.boardType = boardType;
    }
}
