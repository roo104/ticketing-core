package dk.unwire.ticketing.core.domain.ticket.model.factory;

import dk.unwire.ticketing.core.domain.account.model.Account;
import dk.unwire.ticketing.core.domain.product.model.Product;
import dk.unwire.ticketing.core.domain.ticket.model.Ticket;
import org.springframework.stereotype.Component;

@Component
public class TicketFactory {

    public Ticket buildTicket(Account account, Product product) {
        Ticket ticket = new Ticket(account, product);
        ticket.initState();
        return ticket;
    }



}
