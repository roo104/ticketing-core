package dk.unwire.ticketing.core.domain.account.model;

import com.unwire.mticket.util.collection.CollectionUtil;
import dk.unwire.ticketing.spring.rest.common.header.MticketIdentifierType;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Collection;

@Entity
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class Account {

    @Getter
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
	@Column(name = "application_id") @Getter
    private int applicationId;
    @Column(name = "created")
    private ZonedDateTime created;
    @Column(name = "blacklisted")
    private boolean blacklisted;
    @Column(name = "blacklist_expiredate") @Getter
    private ZonedDateTime blacklistExpiredate;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "account")
    private Collection<AccountIdentifier> accountIdentifiers;

    private Account(FindOrCreateAccountVO findOrCreateAccountVO) {
        this.accountIdentifiers = new ArrayList<>();
        this.applicationId = findOrCreateAccountVO.getApplicationId();
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
