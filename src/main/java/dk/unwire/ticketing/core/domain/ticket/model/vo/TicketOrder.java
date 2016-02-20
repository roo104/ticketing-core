package dk.unwire.ticketing.core.domain.ticket.model.vo;

import dk.unwire.ticketing.core.domain.ticket.model.Ticket;
import lombok.Getter;

import java.util.Collection;
import java.util.HashSet;

public class TicketOrder {

    @Getter
    private final Ticket billingTicket;
    @Getter
    private final Collection<Ticket> childTickets;

    public TicketOrder(Ticket billingTicket, Collection<Ticket> childTickets) {
        this.billingTicket = billingTicket;
        this.childTickets = childTickets;
    }

    public PaymentType getPaymentType() {
        return this.billingTicket.getTicketPrice().getPaymentType();
    }

    public Collection<Ticket> getAllTickets() {
        HashSet<Ticket> tickets = new HashSet<>();
        tickets.addAll(this.childTickets);
        tickets.add(this.billingTicket);
        return tickets;
    }
}
