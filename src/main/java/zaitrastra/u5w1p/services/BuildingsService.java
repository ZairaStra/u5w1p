package zaitrastra.u5w1p.services;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import zaitrastra.u5w1p.entities.Building;
import zaitrastra.u5w1p.exceptions.DuplicatedException;
import zaitrastra.u5w1p.exceptions.NotFoundException;
import zaitrastra.u5w1p.exceptions.ValidationException;
import zaitrastra.u5w1p.repositories.BuildingsRepository;

import java.util.List;

@Service
@Slf4j
public class BuildingsService {
    @Autowired
    private BuildingsRepository buildingsRepository;

    // salvo edificio con controllo duplicato
    public Building saveBuilding(Building newBuilding) {
        if (buildingsRepository.findByNameIgnoreCaseAndAddressIgnoreCase(newBuilding.getName(), newBuilding.getAddress()).isPresent()) {
            throw new DuplicatedException("Building with the same name and address already exists");
        }
        return buildingsRepository.save(newBuilding);
    }

    // salvo lista di edifici con controllo duplicati e inserimento dati valido
    public void saveManyBuildings(List<Building> newBuildings) {
        for (Building building : newBuildings) {
            try {
                this.saveBuilding(building);
            } catch (ValidationException exception) {
                log.error(exception.getMessage());
            } catch (DuplicatedException exception) {
                log.error(exception.getMessage());
            }
        }
    }

    //mi serve la ricerca di postazioni per città
    public List<Building> findBuildingByCity(String city) {
        List<Building> buildings = buildingsRepository.findByCityIgnoreCase(city);
        if (buildings.isEmpty()) {
            throw new NotFoundException("No buildings founded for this city");
        }
        return buildings;
    }
}

