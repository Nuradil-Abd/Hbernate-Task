package java15.entities;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "comments")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@SequenceGenerator(name = "comment_seq",sequenceName = "comment_seq", allocationSize = 1)

public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "comment_seq")
    private Long id;
    private String text;
    private LocalDateTime commentDate;

    @ManyToOne
    @JoinColumn(name = "post_id", nullable = false)
    private Post post;

    @ManyToMany(mappedBy = "comments")
    @ToString.Exclude //todo стек-рейс
    private List<User> users;

    @PrePersist
    public void prePersist() {
        this.commentDate = LocalDateTime.now();
    }
}
