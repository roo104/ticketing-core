package dk.unwire.ticketing.core.domain.account.model;

import dk.unwire.ticketing.spring.rest.common.header.MticketIdentifierType;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;


@Entity
@Table(name = "account_identifier")
@NoArgsConstructor(access = AccessLevel.PRIVATE)
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
    @Enumerated(EnumType.ORDINAL)
    private MticketIdentifierType identifierType;
    @Getter
    @ManyToOne
    @JoinColumn(referencedColumnName = "id")
    private Account account;

    private AccountIdentifier(Builder builder) {
        this.applicationId = builder.applicationId;
        this.identifier = builder.identifier;
        this.identifierType = builder.identifierType;
        this.account = builder.account;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static final class Builder {
        private int applicationId;
        private String identifier;
        private MticketIdentifierType identifierType;
        private Account account;

        private Builder() {
        }

        public Builder applicationId(int applicationId) {
            this.applicationId = applicationId;
            return this;
        }

        public Builder identifier(String identifier) {
            this.identifier = identifier;
            return this;
        }

        public Builder identifierType(MticketIdentifierType identifierType) {
            this.identifierType = identifierType;
            return this;
        }

        public Builder account(Account account) {
            this.account = account;
            return this;
        }

        public AccountIdentifier build() {
            return new AccountIdentifier(this);
        }
    }
}
