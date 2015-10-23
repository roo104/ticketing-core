package dk.unwire.ticketing.core.domain.account.model;

import dk.unwire.ticketing.core.domain.account.enums.IdentifierType;
import lombok.Builder;
import lombok.Getter;

import javax.persistence.*;


@Entity
@Table(name = "account_identifier")
public final class AccountIdentifier {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "application_id")
    private int applicationId;
    @Getter
    @Column(name = "identifier_value")
    protected String identifier;
    @Getter
    @Column(name = "identifier_type")
    private int identifierType;
    @Getter
    @ManyToOne
    @JoinColumn(referencedColumnName = "id")
    private Account account;

    @Builder
    public AccountIdentifier(int applicationId, String identifier, Account account, IdentifierType identifierType) {

        this.applicationId = applicationId;
        this.identifier = identifier;
        this.account = account;
        this.identifierType = identifierType.getId();

    }


}
