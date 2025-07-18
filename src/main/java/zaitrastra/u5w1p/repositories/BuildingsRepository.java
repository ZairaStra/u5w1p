package zaitrastra.u5w1p.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import zaitrastra.u5w1p.entities.Building;

import java.util.List;
import java.util.Optional;

public interface BuildingsRepository extends JpaRepository<Building, Long> {

    Optional<Building> findByNameIgnoreCase(String name);

    Optional<Building> findByNameIgnoreCaseAndAddressIgnoreCase(String name, String address);

    List<Building> findByCityIgnoreCase(String city);

}
