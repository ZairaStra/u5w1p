package zaitrastra.u5w1p.services;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import zaitrastra.u5w1p.repositories.WorkstationsRepository;

@Service
@Slf4j
public class WorkstationsService {
    @Autowired
    private WorkstationsRepository workstationsRepository;

    @Autowired
    private BuildingsService buildingsService;

    //devo poter cercare postazioni di un dato tipo per città
    //USO IL METODO List<Building> findByCityIgnoreCase(String city) di BuildingSErvice e la mixo con ricerca per tipo della repository

//    public List<Workstation> findWorkstationByTypeAndCity(WorkstationType type, String city) {
//        List<Building> buildingsPerCity = buildingsService.findBuildingByCity(city);
//        List<Workstation> workstationType=workstationsRepository.findByWorkstationType(type);
//
//        //come interseco le due liste???
//        //filter?
//        //forEach?
//
//
//    }


    //mi serve un modo per cambiare lo stato della postazione - come quello del tavolo della pizzeria
}
