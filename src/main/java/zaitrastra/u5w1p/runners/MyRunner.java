package zaitrastra.u5w1p.runners;

import com.github.javafaker.Faker;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import zaitrastra.u5w1p.entities.Building;
import zaitrastra.u5w1p.entities.User;
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

        //lista di edifici

        List<Building> newBuildings = new ArrayList<>();

        for (int i = 0; i < 10; i++) {
            Building building = new Building(faker.funnyName().name(), faker.address().fullAddress(), faker.gameOfThrones().city());
            newBuildings.add(building);
        }
//        buildingsService.saveManyBuildings(newBuildings);

        //lista di postazioni


        //salvo le prenotazioni una per volta

    }
}
