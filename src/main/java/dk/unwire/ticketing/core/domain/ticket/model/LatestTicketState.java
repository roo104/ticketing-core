package dk.unwire.ticketing.core.domain.ticket.model;

import lombok.Getter;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class LatestTicketState {

    @Getter
    @Column(name = "latest_ticket_state_id")
    private Long latestTicketStateId;
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
