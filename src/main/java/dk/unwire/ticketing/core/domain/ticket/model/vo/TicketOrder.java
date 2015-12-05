package dk.unwire.ticketing.core.domain.ticket.model.vo;

import dk.unwire.ticketing.core.domain.ticket.model.Ticket;
import lombok.Getter;

import java.util.Collection;

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
}
