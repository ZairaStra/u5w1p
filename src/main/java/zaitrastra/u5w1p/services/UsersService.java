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
    public User saveUser(User newUser) {
        if (usersRepository.findByUsernameIgnoreCase(newUser.getUsername()).isPresent()) {
            throw new DuplicatedException("Username not available");
        }

        if (usersRepository.findByFullNameIgnoreCaseAndEmailIgnoreCase(newUser.getFullName(), newUser.getEmail()).isPresent()) {
            throw new DuplicatedException(("You already have an account"));
        }
        return usersRepository.save(newUser);
    }

    //salvo lista di user con controllo duplicati e inserimento dati valido
    public void saveManyUsers(List<User> newUsers) {
        for (User user : newUsers) {
            try {
                this.saveUser(user);
            } catch (ValidationException exception) {
                log.error(exception.getMessage());
            } catch (DuplicatedException exception) {
                log.error(exception.getMessage());
            }
        }
    }
}
