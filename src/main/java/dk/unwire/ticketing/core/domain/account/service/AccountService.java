package dk.unwire.ticketing.core.domain.account.service;

import dk.unwire.ticketing.core.domain.account.enums.IdentifierType;
import dk.unwire.ticketing.core.domain.account.model.Account;
import dk.unwire.ticketing.core.domain.account.model.AccountIdentifier;
import dk.unwire.ticketing.core.domain.account.model.FindOrCreateAccountVO;
import dk.unwire.ticketing.core.domain.account.repository.AccountIdentifierRepository;
import dk.unwire.ticketing.core.domain.account.repository.AccountRepository;
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
        IdentifierType identifierType = findOrCreateAccountVO.getIdentifierType();
        int applicationId = findOrCreateAccountVO.getApplicationId();

        AccountIdentifier accountIdentifier = this.accountIdentifierRepository.findByIdentifierAndIdentifierTypeAndApplicationId(identifier, identifierType.getId(), applicationId);

        if (accountIdentifier == null) {
            Account account = Account.fromFindOrCreateAccountVO(findOrCreateAccountVO);
            this.accountRepository.save(account);
            accountIdentifier = account.getAccountIdentifier(identifierType);
        }

        return accountIdentifier;
    }
}
