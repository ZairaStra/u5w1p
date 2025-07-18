package zaitrastra.u5w1p.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import zaitrastra.u5w1p.entities.User;

import java.util.Optional;

//qui trovo i metodi che mi offre già Spring - devo solo chiamarli
//a seconda di cosa mi serve nel Service per i metodi creati da me

public interface UsersRepository extends JpaRepository<User, Long> {

    //cerco per username - se non esiste da null, non errore - Optional
    Optional<User> findByUsernameIgnoreCase(String username);

    //devo controllare che ci possa essere un solo utente con un dato nome e una data mail
    //mi serve per salvare un nuovo utente - o una lista di utenti
    Optional<User> findByFullNameIgnoreCaseAndEmailIgnoreCase(String fullName, String email);
}
