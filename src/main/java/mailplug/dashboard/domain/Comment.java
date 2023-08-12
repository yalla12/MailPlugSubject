package mailplug.dashboard.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Getter
@NoArgsConstructor
@Entity
public class Comment extends Timestamped {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long commentId;

    @JoinColumn(name = "writerId")
    @ManyToOne(fetch = FetchType.EAGER)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Writer writer;

    @JoinColumn(name = "postId")
    @ManyToOne(fetch = FetchType.EAGER)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Post post;

    @Column
    private String contents;

    public Comment(Writer writer, Post post, String contents) {
        this.writer = writer;
        this.post = post;
        this.contents = contents;
    }

    public void updateComment(String contents) {
        this.contents = contents;
    }


}
