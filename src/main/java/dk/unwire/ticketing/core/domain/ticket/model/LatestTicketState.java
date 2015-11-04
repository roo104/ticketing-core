package dk.unwire.ticketing.core.domain.ticket.model;

import lombok.Getter;

import javax.persistence.*;

@Embeddable
public class LatestTicketState {

    @Getter
    @OneToOne(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "latest_ticket_state_id", referencedColumnName = "id")
    private TicketState latestTicketState;
    @Getter
    @Column(name = "ticket_state")
    private Integer ticketState;
    @Getter
    @Column(name = "transaction_state")
    private Integer transactionState;
    @Getter
    @Column(name = "ACTION")
    private Integer action;
    @Getter
    @Column(name = "error_code")
    private Integer errorCode;
}
