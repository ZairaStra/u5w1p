package zaitrastra.u5w1p.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import zaitrastra.u5w1p.entities.Reservation;

public interface ReservationsRepository extends JpaRepository<Reservation, Long> {
}
