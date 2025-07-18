package zaitrastra.u5w1p.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import zaitrastra.u5w1p.entities.Reservation;
import zaitrastra.u5w1p.entities.User;
import zaitrastra.u5w1p.entities.Workstation;

import java.time.LocalDate;

public interface ReservationsRepository extends JpaRepository<Reservation, Long> {
    //devo cercare per data e utente e per data e postazione - mi basta saper e se esoste o meno
    //per le eccexioni nel metodo save

    boolean existsByWorkstationAndDate(Workstation workstation, LocalDate date);

    boolean existsByUserAndDate(User user, LocalDate date);
}
