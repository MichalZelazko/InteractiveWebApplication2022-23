package pl.dmcs.iwamzelazko.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.dmcs.iwamzelazko.model.Account;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {
    Account findById(long id);
}
