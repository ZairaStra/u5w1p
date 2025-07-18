package zaitrastra.u5w1p.services;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import zaitrastra.u5w1p.entities.Reservation;
import zaitrastra.u5w1p.exceptions.AlreadyBookedException;
import zaitrastra.u5w1p.exceptions.DuplicatedException;
import zaitrastra.u5w1p.repositories.ReservationsRepository;

import java.util.List;

@Service
@Slf4j
public class ReservationsService {
    @Autowired
    private ReservationsRepository reservationsRepository;

    //mi serve solo il metodo di salvataggio di singola prenotazione
    public void saveReservation(Reservation reservation) {

        if (reservationsRepository.existsByWorkstationAndDate(reservation.getWorkstation(), reservation.getDate())) {
            throw new AlreadyBookedException("This workstation has been already booked");
        }

        if (reservationsRepository.existsByUserAndDate(reservation.getUser(), reservation.getDate())) {

            throw new DuplicatedException("You have already booked a workstation for this day");
        }

        reservationsRepository.save(reservation);
        log.info("You have correctly reserved your workstation for " + reservation.getDate());
    }

    //per recuperare dati da db
    public List<Reservation> findAllReservations() {
        return reservationsRepository.findAll();
    }

}
