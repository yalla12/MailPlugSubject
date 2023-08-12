package mailplug.dashboard.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Post extends Timestamped {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long postId;

    @Column(nullable = false)
    private String title;

    @JoinColumn(name = "boardId")
    @ManyToOne(fetch = FetchType.EAGER)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Board board;

    @JoinColumn(name = "writer_id")
    @ManyToOne(fetch = FetchType.EAGER)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Writer writer;

    @Column(nullable = false)
    private String contents;

    @Column(nullable = false)
    private int commentsCount;

    public Post(String title, Board board, Writer writer, String contents ) {
        this.title = title;
        this.board = board;
        this.writer = writer;
        this.contents = contents;
    }

    public void updatePost(String title, String contents) {
        this.title = title;
        this.contents = contents;
    }

    public void commentsCount(int commentsCount) {
        this.commentsCount = commentsCount;
    }
}
