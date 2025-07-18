package zaitrastra.u5w1p.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import zaitrastra.u5w1p.entities.Workstation;
import zaitrastra.u5w1p.entities.enums.WorkstationType;

import java.util.List;

public interface WorkstationsRepository extends JpaRepository<Workstation, Long> {

    //cerco per tipo di WOrkstation
    List<Workstation> findByWorkstationType(WorkstationType type);


}
