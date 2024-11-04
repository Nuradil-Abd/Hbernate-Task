package java15.entities;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "posts")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@SequenceGenerator(name = "post_seq",sequenceName = "post_seq", allocationSize = 1)

public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "post_seq")
    private Long id;
    private String image;
    private String description;
    private LocalDateTime created;
    @ManyToOne
    @JoinColumn(name = "user_id")
    @ToString.Exclude //todo стек-рейс
    private User user;

    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL, orphanRemoval = true)
    @ToString.Exclude //todo стек-рейс
    private List<Comment> comments;
}
