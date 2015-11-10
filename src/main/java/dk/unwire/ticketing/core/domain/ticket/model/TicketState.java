package dk.unwire.ticketing.core.domain.ticket.model;

import dk.unwire.ticketing.core.domain.ticket.state.CombinedState;
import lombok.Getter;

import javax.persistence.*;

@Entity
@Table(name = "ticket_state")
public class TicketState {

    @Id
    @Getter
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Getter
    @Column(name = "ticket_state")
    private int ticketState;
    @Getter
    @Column(name = "transaction_state")
    private int transactionState;
    @Getter
    @Column(name = "action")
    private int action;
    @Getter
    @Column(name = "error_code")
    private int errorCode;

    private TicketState() {
    }

    public TicketState(CombinedState state) {
        this.ticketState = state.ticketState().getState();
        this.transactionState = state.transactionState().getState();

    }
}
