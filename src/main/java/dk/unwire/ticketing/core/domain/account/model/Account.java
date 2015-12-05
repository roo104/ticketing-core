package dk.unwire.ticketing.core.domain.account.model;

import com.unwire.mticket.util.collection.CollectionUtil;
import dk.unwire.ticketing.spring.rest.common.header.MticketIdentifierType;
import lombok.Getter;

import javax.persistence.*;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Collection;

@Entity
public final class Account {

    @Getter
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "application_id")
    @Getter
    private final Integer applicationId;
    @Column(name = "created")
    private ZonedDateTime created;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "account")
    private Collection<AccountIdentifier> accountIdentifiers;

    private Account() {
        this.created = null;
        this.accountIdentifiers = new ArrayList<>();
        this.applicationId = null;
    }

    public Account(Integer applicationId, MticketIdentifierType identifierType, String identifierValue) {
        this.created = ZonedDateTime.now(ZoneOffset.UTC);
        this.accountIdentifiers = new ArrayList<>();
        this.applicationId = applicationId;
        addAccountIdentifier(identifierType, identifierValue);
    }

    private Account(FindOrCreateAccountVO findOrCreateAccountVO) {
        this(findOrCreateAccountVO.getApplicationId(), findOrCreateAccountVO.getIdentifierType(), findOrCreateAccountVO.getIdentifier());
    }

    public static Account findOrCreateAccount(FindOrCreateAccountVO findOrCreateAccountVO) {

        return new Account(findOrCreateAccountVO);
    }

    public void addAccountIdentifier(MticketIdentifierType identifierType, String identifierValue) {
        AccountIdentifier accountIdentifier = AccountIdentifier.builder()
                .applicationId(this.applicationId)
                .identifier(identifierValue)
                .identifierType(identifierType)
                .account(this)
                .build();
        addAccountIdentifier(accountIdentifier);
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
        return getAccountIdentifierValue(MticketIdentifierType.MSISDN);
    }

    /**
     * Tries to get Car Registration Number from account identifers.
     *
     * @return String represending a Car Registration Number or NULL of no Car Registration Number is assosiated with this account.
     */
    public String getCarRegistrationNumber() {
        return getAccountIdentifierValue(MticketIdentifierType.LICENSE_PLATE);
    }

    public String getAccountIdentifierValue(MticketIdentifierType identifierType) {
        String identifierValue = null;

        AccountIdentifier identifier = getAccountIdentifier(identifierType);
        if (identifier != null) {
            identifierValue = identifier.getIdentifier();
        }
        return identifierValue;
    }

    public AccountIdentifier getAccountIdentifier(MticketIdentifierType type) {
        AccountIdentifier accountIdentifier = null;
        if (CollectionUtil.isNotEmpty(this.accountIdentifiers)) {
            for (AccountIdentifier identifier : this.accountIdentifiers) {
                if (identifier.getAccount().getId() == this.id && identifier.getIdentifierType().equals(type)) {
                    accountIdentifier = identifier;
                    break;
                }
            }
        }
        return accountIdentifier;
    }

    public ZonedDateTime getCreated() {
        return this.created;
    }

    public int getApplicationId() {
        return this.applicationId;
    }
}
