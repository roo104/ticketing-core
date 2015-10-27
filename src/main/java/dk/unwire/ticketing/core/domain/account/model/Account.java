package dk.unwire.ticketing.core.domain.account.model;

import com.unwire.mticket.util.collection.CollectionUtil;
import dk.unwire.ticketing.core.domain.account.enums.IdentifierType;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

@Entity
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class Account {
    public static final int ZERO_PURCHASES = 0;
    @Getter
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "application_id")
    private int applicationId;
    @Getter
    @Column(name = "msisdn")
    private Long msisdn;
    @Column(name = "created")
    private ZonedDateTime created;
    @Column(name = "blacklisted")
    private boolean blacklisted;
    @Column(name = "amount_used")
    private int amountUsed;
    @Column(name = "buy_attempts")
    private int buyAttempts;
    @Column(name = "successful_purchases")
    private int successfulPurchases;
    @Column(name = "blacklist_expiredate")
    private ZonedDateTime blacklistExpiredate;
    @Column(name = "billing_account_id")
    private int billingAccountId;
    @Column(name = "ars_account_id")
    private long arsAccountId;
    @Column(name = "ars_user_id")
    private long arsUserId;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "account")
    private Collection<AccountIdentifier> accountIdentifiers;

    private Account(FindOrCreateAccountVO findOrCreateAccountVO) {
        this.accountIdentifiers = new ArrayList<>();
        this.applicationId = findOrCreateAccountVO.getApplicationId();
        this.successfulPurchases = ZERO_PURCHASES;
        this.created = ZonedDateTime.now(ZoneOffset.UTC);

        AccountIdentifier accountIdentifier = AccountIdentifier.builder()
                .applicationId(this.applicationId)
                .identifier(findOrCreateAccountVO.getIdentifier())
                .identifierType(findOrCreateAccountVO.getIdentifierType())
                .account(this)
                .build();
        addAccountIdentifier(accountIdentifier);
    }

    public static Account findOrCreateAccount(FindOrCreateAccountVO findOrCreateAccountVO) {

        return new Account(findOrCreateAccountVO);
    }

    public void addAccountIdentifier(AccountIdentifier accountIdentifier) {
        this.accountIdentifiers.add(accountIdentifier);
    }

    /**
     * Tries to get MSISDN from account identifers.
     *
     * @return String represending an MSISDN or NULL of no MSISDN is assosiated with this account.
     */
    public String getMsisdn() {
        return getAccountIdentifierValue(IdentifierType.MSISDN);
    }

    /**
     * Tries to get Car Registration Number from account identifers.
     *
     * @return String represending a Car Registration Number or NULL of no Car Registration Number is assosiated with this account.
     */
    public String getCarRegistrationNumber() {
        return getAccountIdentifierValue(IdentifierType.LICENSE_PLATE);
    }

    public String getAccountIdentifierValue(IdentifierType identifierType) {
        String identifierValue = null;

        AccountIdentifier identifier = getAccountIdentifier(identifierType);
        if (identifier != null) {
            identifierValue = identifier.getIdentifier();
        }
        return identifierValue;
    }

    public AccountIdentifier getAccountIdentifier(IdentifierType type) {
        AccountIdentifier accountIdentifier = null;
        if (CollectionUtil.isNotEmpty(this.accountIdentifiers)) {
            Iterator<AccountIdentifier> iterator = this.accountIdentifiers.iterator();
            while (iterator.hasNext()) {
                AccountIdentifier identifier = iterator.next();
                if (identifier.getAccount().getId() == this.id && identifier.getIdentifierType() == type) {
                    accountIdentifier = identifier;
                    break;
                }
            }
        }
        return accountIdentifier;
    }
}
