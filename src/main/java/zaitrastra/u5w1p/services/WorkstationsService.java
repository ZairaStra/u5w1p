package zaitrastra.u5w1p.services;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import zaitrastra.u5w1p.entities.Building;
import zaitrastra.u5w1p.entities.Workstation;
import zaitrastra.u5w1p.entities.enums.WorkstationType;
import zaitrastra.u5w1p.exceptions.DuplicatedException;
import zaitrastra.u5w1p.exceptions.NotFoundException;
import zaitrastra.u5w1p.exceptions.ValidationException;
import zaitrastra.u5w1p.repositories.WorkstationsRepository;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class WorkstationsService {
    @Autowired
    private WorkstationsRepository workstationsRepository;

    @Autowired
    private BuildingsService buildingsService;

    //salvo workstation con controllo duplicato
    public void saveWorkstation(Workstation newWorkstation) {
        //possono esserci più workspace dello stesso tipo per un edicio, mi serve la descrizione
        if (workstationsRepository.findByDescriptionIgnoreCaseAndBuilding(newWorkstation.getDescription(), newWorkstation.getBuilding()).isPresent()) {
            throw new DuplicatedException("There is already a workstation with this description in this building");
        }

        workstationsRepository.save(newWorkstation);

        log.info("Workstation " + newWorkstation.getDescription() + " has been saved");
    }

    //salvo lista di workstation con controllo duplicati e inserimento dati valido
    public void saveManyWorkstations(List<Workstation> newWorkstations) {
        for (Workstation workstation : newWorkstations) {
            try {
                this.saveWorkstation(workstation);
            } catch (ValidationException | DuplicatedException exception) {
                log.error(exception.getMessage());
            }
        }
    }

    //devo poter cercare postazioni di un dato tipo per città
    //USO IL METODO List<Building> findByCityIgnoreCase(String city) di BuildingSErvice e la mixo con ricerca per tipo della repository

//    public List<Workstation> findWorkstationByTypeAndCity(WorkstationType type, String city) {
//        List<Building> buildingsPerCity = buildingsService.findBuildingByCity(city);
//        List<Workstation> workstationType = workstationsRepository.findByWorkstationType(type);
//
//        //come interseco le due liste???
//        //filter?
//        //forEach?
//
//
//    }

    public List<Workstation> findWorkstationByTypeAndCity(WorkstationType type, String city) {
        List<Building> buildingPerCity = buildingsService.findBuildingByCity(city);
        List<Workstation> filteredWorkstation = new ArrayList<>();

        for (Building building : buildingPerCity) {
            List<Workstation> workstations = workstationsRepository.findByWorkstationTypeAndBuilding(type, building);
            filteredWorkstation.addAll(workstations);
        }

        if (filteredWorkstation.isEmpty()) {
            throw new NotFoundException("There are no workstations of type " + type + " in " + city);
        }

        return filteredWorkstation;
    }

    //mi serve un modo per cambiare lo stato della postazione - come quello del tavolo della pizzeria
    //no perchè non è un enum, nè un boolean, controllo la data nel metodo

    //per recuperare dati da db
    public List<Workstation> findAllWorkstations() {
        return workstationsRepository.findAll();
    }
}
