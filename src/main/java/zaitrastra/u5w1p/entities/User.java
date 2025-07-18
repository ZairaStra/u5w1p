package zaitrastra.u5w1p.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "Users")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter(AccessLevel.NONE)
    @Column(name = "user_id", nullable = false)
    private Long id;

    private String fullName;
    private String username;
    private String email;

    @OneToMany(mappedBy = "user")
    private List<Reservation> reservations;
}
