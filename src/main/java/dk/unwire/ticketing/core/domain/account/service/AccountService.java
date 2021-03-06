package dk.unwire.ticketing.core.domain.account.service;

import dk.unwire.ticketing.core.domain.account.model.Account;
import dk.unwire.ticketing.core.domain.account.model.AccountIdentifier;
import dk.unwire.ticketing.core.domain.account.model.FindOrCreateAccountVO;
import dk.unwire.ticketing.core.domain.account.repository.AccountIdentifierRepository;
import dk.unwire.ticketing.core.domain.account.repository.AccountRepository;
import dk.unwire.ticketing.spring.rest.common.header.MticketIdentifierType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccountService {
    @Autowired
    private AccountRepository accountRepository;
    @Autowired
    private AccountIdentifierRepository accountIdentifierRepository;

    public AccountIdentifier findOrCreateAccount(FindOrCreateAccountVO findOrCreateAccountVO) {
        String identifier = findOrCreateAccountVO.getIdentifier();
		MticketIdentifierType identifierType = findOrCreateAccountVO.getIdentifierType();
        int applicationId = findOrCreateAccountVO.getApplicationId();

        AccountIdentifier accountIdentifier = this.accountIdentifierRepository.findByIdentifierAndIdentifierTypeAndApplicationId(
                identifier, identifierType, applicationId);

        if (accountIdentifier == null) {
            Account account = Account.findOrCreateAccount(findOrCreateAccountVO);
            this.accountRepository.save(account);
            accountIdentifier = account.getAccountIdentifier(identifierType);
        }

        return accountIdentifier;
    }
}
