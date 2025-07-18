package zaitrastra.u5w1p.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import zaitrastra.u5w1p.entities.Building;
import zaitrastra.u5w1p.entities.Workstation;
import zaitrastra.u5w1p.entities.enums.WorkstationType;

import java.util.List;
import java.util.Optional;

public interface WorkstationsRepository extends JpaRepository<Workstation, Long> {

//devo filtrare per description perchè è l'unica cosa univoca nel moemnto in cui salvo
    
    Optional<Workstation> findByDescriptionIgnoreCaseAndBuilding(String description, Building building);

    //cerco per tipo di WOrkstation
    List<Workstation> findByWorkstationTypeAndBuilding(WorkstationType type, Building Building);


}
