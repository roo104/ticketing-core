package dk.unwire.ticketing.core.domain.account.repository;

import dk.unwire.ticketing.core.domain.account.model.AccountIdentifier;
import dk.unwire.ticketing.spring.rest.common.header.MticketIdentifierType;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountIdentifierRepository extends CrudRepository<AccountIdentifier, Integer> {
    AccountIdentifier findByIdentifierAndIdentifierTypeAndApplicationId(String identifier,
																		MticketIdentifierType identifierType,
																		int applicationId);
}
