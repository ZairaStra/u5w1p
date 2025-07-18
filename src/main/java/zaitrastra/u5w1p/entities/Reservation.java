package zaitrastra.u5w1p.entities;


import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

//creo Reservation come una tabella di congiunzione tra User e Workstation
//come se fosse una ManyToMany tra le due User e Workstation e qui trovassi la lista degli abbinamenti degli id per ciascuna relazione
@Entity
@Table(name = "Reservations")
@Getter
@Setter
@NoArgsConstructor

@ToString
public class Reservation {
    @Id
    @Column(name = "reservation_code", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter(AccessLevel.NONE)
    private Long id;

    @Column(name = "reservation_date")
    private LocalDate date;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "workstation_id", nullable = false)
    private Workstation workstation;

    public Reservation(Workstation workstation, User user, LocalDate date) {
        this.workstation = workstation;
        this.user = user;
        this.date = date;
    }
}
