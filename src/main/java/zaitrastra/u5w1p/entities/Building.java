package zaitrastra.u5w1p.entities;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "Buildings")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Building {
    private String name;
    private String address;
    private String city;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter(AccessLevel.NONE)
    @Column(name = "building_id", nullable = false)
    private Long id;
}
