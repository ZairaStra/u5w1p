package zaitrastra.u5w1p.services;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import zaitrastra.u5w1p.repositories.ReservationsRepository;

@Service
@Slf4j
public class ReservationsService {
    @Autowired
    private ReservationsRepository reservationsRepository;
}
