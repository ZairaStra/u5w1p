package zaitrastra.u5w1p.entities;

import jakarta.persistence.*;
import lombok.*;
import zaitrastra.u5w1p.entities.enums.WorkstationType;

import java.util.List;

@Entity
@Table(name = "Workstations")
@Getter
@Setter
@NoArgsConstructor

@ToString
public class Workstation {
    private String description;

    @Enumerated(EnumType.STRING)
    private WorkstationType workstationType;
    private int maxNumbOccupants;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter(AccessLevel.NONE)
    @Column(name = "workstation_id", nullable = false)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "building_id", nullable = false)
    private Building building;

    @OneToMany(mappedBy = "workstation")
    private List<Reservation> reservations;

    public Workstation(int maxNumbOccupants, WorkstationType workstationType, String description, Building building, List<Reservation> reservations) {
        this.maxNumbOccupants = maxNumbOccupants;
        this.workstationType = workstationType;
        this.description = description;
        this.building = building;
        this.reservations = reservations;
    }
}
