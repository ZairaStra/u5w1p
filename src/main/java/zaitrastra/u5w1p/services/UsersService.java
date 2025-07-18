package zaitrastra.u5w1p.services;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import zaitrastra.u5w1p.entities.User;
import zaitrastra.u5w1p.exceptions.DuplicatedException;
import zaitrastra.u5w1p.exceptions.ValidationException;
import zaitrastra.u5w1p.repositories.UsersRepository;

import java.util.List;

@Service
@Slf4j
public class UsersService {
    @Autowired
    private UsersRepository usersRepository;

    //salvo user con controllo duplicato
    public void saveUser(User newUser) {
        if (usersRepository.findByUsernameIgnoreCase(newUser.getUsername()).isPresent()) {
            throw new DuplicatedException("Username not available");
        }

        if (usersRepository.findByFullNameIgnoreCaseAndEmailIgnoreCase(newUser.getFullName(), newUser.getEmail()).isPresent()) {
            throw new DuplicatedException(("You already have an account"));
        }
        usersRepository.save(newUser);

        log.info("User" + newUser.getUsername() + " has been saved");
    }

    //salvo lista di user con controllo duplicati e inserimento dati valido
    public void saveManyUsers(List<User> newUsers) {
        for (User user : newUsers) {
            try {
                this.saveUser(user);
            } catch (ValidationException | DuplicatedException exception) {
                log.error(exception.getMessage());
            }
        }
    }
}
