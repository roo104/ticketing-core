package dk.unwire.ticketing.core.domain.ticket.model;

import dk.unwire.ticketing.core.domain.ticket.state.CombinedState;
import dk.unwire.ticketing.core.domain.ticket.state.TicketStateType;
import dk.unwire.ticketing.core.domain.ticket.state.TransactionStateType;
import dk.unwire.ticketing.core.domain.ticket.state.TransitionType;
import lombok.Getter;

import javax.persistence.*;
import java.util.Collection;
import java.util.HashSet;

@Embeddable
public class TicketStateInfo {

    @Getter
    @OneToOne(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "id", referencedColumnName = "latest_ticket_state_id")
    private TicketState latestTicketState;
    @Getter
    @Column(name = "ticket_state")
    private int ticketState;
    @Getter
    @Column(name = "transaction_state")
    private int transactionState;
    @Getter
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "ticket_id", referencedColumnName = "id")
    private Collection<TicketState> ticketStates;
    @Getter
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "ticket_id", referencedColumnName = "id")
    private Collection<LogEntry> logEntries;

    protected TicketStateInfo() {
        this.ticketStates = new HashSet<>();
        this.logEntries = new HashSet<>();
    }

    public CombinedState asCombinedState() {
        return new CombinedState(TransitionType.INITIAL,
                TicketStateType.fromValue(this.ticketState),
                TransactionStateType.fromValue(this.transactionState));
    }

    public void updateState(CombinedState nextState) {
        this.ticketState = nextState.ticketState().getState();
        this.transactionState = nextState.transactionState().getState();
        this.latestTicketState = new TicketState(nextState);
        this.ticketStates.add(this.latestTicketState);
        this.logEntries.add(new LogEntry(this.latestTicketState));
    }
}
