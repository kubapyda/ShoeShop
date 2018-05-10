package pl.shoeshop.shoeshop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.shoeshop.shoeshop.entity.Account;

public interface AccountRepository extends JpaRepository<Account, Long> {

    Account findByLogin(String login);
}
