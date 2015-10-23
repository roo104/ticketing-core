package dk.unwire.ticketing.core.domain.account.repository;

import dk.unwire.ticketing.core.domain.account.model.Account;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepository extends CrudRepository<Account, Integer> {
    Account findByMsisdn(long msisdn);
}
