package zaitrastra.u5w1p.runners;

import com.github.javafaker.Faker;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import zaitrastra.u5w1p.entities.Building;
import zaitrastra.u5w1p.entities.User;
import zaitrastra.u5w1p.entities.Workstation;
import zaitrastra.u5w1p.entities.enums.WorkstationType;
import zaitrastra.u5w1p.services.BuildingsService;
import zaitrastra.u5w1p.services.ReservationsService;
import zaitrastra.u5w1p.services.UsersService;
import zaitrastra.u5w1p.services.WorkstationsService;

import java.util.ArrayList;
import java.util.List;

@Component
@Slf4j
public class MyRunner implements CommandLineRunner {

    @Autowired
    private BuildingsService buildingsService;

    @Autowired
    private ReservationsService reservationsService;

    @Autowired
    private UsersService usersService;

    @Autowired
    private WorkstationsService workstationsService;

    @Override
    public void run(String... args) throws Exception {

        //creo lista utenti con faker
        Faker faker = new Faker();

        List<User> newUsers = new ArrayList<>();
//        User u1 = new User(faker.harryPotter().character(), faker.harryPotter().spell(), faker.internet().emailAddress());
        //ciclo?
        for (int i = 0; i < 15; i++) {
            User user = new User(faker.harryPotter().character(), faker.harryPotter().spell(), faker.internet().emailAddress());
            newUsers.add(user);
        }
//        usersService.saveManyUsers(newUsers);
        List<User> users = usersService.findAllUsers();

        //lista di edifici

        List<Building> newBuildings = new ArrayList<>();


        for (int i = 0; i < 10; i++) {
            Building building = new Building(faker.funnyName().name(), faker.address().fullAddress(), faker.gameOfThrones().city());
            newBuildings.add(building);
        }
//        buildingsService.saveManyBuildings(newBuildings);
        List<Building> buildings = buildingsService.findAllBuildings();
        for (Building building : buildings) {
            System.out.println(building);
        }

        //lista di postazioni
        //non mi salva più gli edifici, non posso testare niente

        List<Workstation> newWorkstations = new ArrayList<>();
        Workstation ws1 = new Workstation(4, WorkstationType.PRIVATE, "Workstation 1 in " + buildings.get(0).getName(), buildings.get(0), new ArrayList<>());
        Workstation ws2 = new Workstation(6, WorkstationType.OPEN_SPACE, "Workstation 2 in " + buildings.get(1).getName(), buildings.get(1), new ArrayList<>());
        Workstation ws3 = new Workstation(2, WorkstationType.MEETING_ROOM, "Workstation 3 in " + buildings.get(2).getName(), buildings.get(2), new ArrayList<>());
        Workstation ws4 = new Workstation(8, WorkstationType.PRIVATE, "Workstation 4 in " + buildings.get(3).getName(), buildings.get(3), new ArrayList<>());
        Workstation ws5 = new Workstation(10, WorkstationType.OPEN_SPACE, "Workstation 5 in " + buildings.get(4).getName(), buildings.get(4), new ArrayList<>());

        workstationsService.saveWorkstation(ws1);
        workstationsService.saveWorkstation(ws2);
        workstationsService.saveWorkstation(ws3);
        workstationsService.saveWorkstation(ws4);
        workstationsService.saveWorkstation(ws5);


        //salvo le prenotazioni una per volta
        //se non ho le workstation, che non posso salvare senza buildings, non posso salvare le prenotazioni

    }
}
