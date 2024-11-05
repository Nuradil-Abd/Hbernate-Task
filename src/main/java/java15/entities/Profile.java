package java15.entities;

import jakarta.persistence.*;
import java15.enums.Gender;
import lombok.*;

import java.time.LocalDate;
@Entity
@Table(name = "profiles")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@SequenceGenerator(name = "profile_seq",sequenceName = "profile_seq",allocationSize = 1)

public class Profile {
    @Id

    private Long id;
    private String fullName;
    private LocalDate dateOfBirth;
    @Enumerated(EnumType.STRING)
    private Gender gender;
    private String bio;

    @OneToOne(mappedBy = "profile", cascade = CascadeType.ALL)
    @ToString.Exclude //todo чтоб избежать стек-рейс
    private User user;

    public Profile(String fullName, LocalDate dateOfBirth, Gender gender, String bio, User user) {
        this.fullName = fullName;
        this.dateOfBirth = dateOfBirth;
        this.gender = gender;
        this.bio = bio;
        this.user = user;
    }
}
