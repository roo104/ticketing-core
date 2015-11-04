package dk.unwire.ticketing.core.domain.ticket.model;

import dk.unwire.ticketing.core.common.model.PropertyMap;
import dk.unwire.ticketing.core.domain.account.model.Account;
import dk.unwire.ticketing.core.domain.application.model.Application;
import dk.unwire.ticketing.core.domain.product.model.Product;
import lombok.Getter;

import javax.persistence.*;
import java.time.ZonedDateTime;
import java.util.Collection;

@Entity
@AssociationOverride(name = "properties", joinColumns = @JoinColumn(name = "ticket_id", referencedColumnName = "id"))
public class Ticket extends PropertyMap<TicketProperty> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    @Column(name = "id")
    private long id;
    @Getter
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "application_id", referencedColumnName = "id")
    private Application application;
    @Getter
    @Column(name = "serial_code")
    private String serialCode;
    @Getter
    @Column(name = "alpha_shortcode")
    private String alphaShortcode;
    @Getter
    @Column(name = "control_code")
    private String controlCode;
    @Getter
    @Column(name = "buy_time")
    private ZonedDateTime buyTime;
    @Getter
    @Column(name = "start_time")
    private ZonedDateTime startTime;
    @Getter
    @Column(name = "expire_time")
    private ZonedDateTime expireTime;
    @Getter
    @Embedded
    private TicketPrice ticketPrice;
    @Getter
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "account_id", referencedColumnName = "id")
    private Account account;
    @Getter
    @Column(name = "test")
    private boolean test;
    @Getter
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "tariff", referencedColumnName = "id")
    private Product product;
    @Getter
    @Column(name = "type")
    private String type;
    @Getter
    @Embedded
    private LatestTicketState latestTicketState;
    @Getter
    @Column(name = "order_channel_id")
    private Integer orderChannelId;
    @Getter
    @Column(name = "ticket_kinship")
    private Integer ticketKinship;
    @Getter
    @Column(name = "parent_ticket_id")
    private Long parentTicketId;
    @Getter
    @Column(name = "ticket_variant")
    private Integer ticketVariant;
    @Getter
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "ticket_id", referencedColumnName = "id")
    private Collection<LogEntry> logEntries;
    @Getter
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "ticket_id", referencedColumnName = "id")
    private Collection<TicketState> ticketStates;

    private Ticket() {

    }

    public Ticket(Account account, Product product) {
        this.account = account;
        this.product = product;
        this.buyTime = ZonedDateTime.now();
        this.type = product.getType();
    }


    public void initState() {

    }
}