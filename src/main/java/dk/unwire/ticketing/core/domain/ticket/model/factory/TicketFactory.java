package dk.unwire.ticketing.core.domain.ticket.model.factory;

import dk.unwire.ticketing.core.domain.account.model.Account;
import dk.unwire.ticketing.core.domain.product.model.Product;
import dk.unwire.ticketing.core.domain.ticket.model.Ticket;
import dk.unwire.ticketing.core.domain.ticket.model.exception.TicketCreationException;
import dk.unwire.ticketing.core.domain.ticket.model.vo.PaymentType;
import dk.unwire.ticketing.core.domain.ticket.model.vo.TicketIssuingType;
import dk.unwire.ticketing.core.domain.ticket.model.vo.TicketKinship;
import dk.unwire.ticketing.core.domain.ticket.model.vo.TicketOrder;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.HashSet;

@Component
public class TicketFactory {

    private static final int ONE_PRODUCT = 1;
    private static final int ZERO_PRICE = 0;

    public TicketOrder createTickets(TicketIssuingType ticketIssuingType, Account account, Collection<Product> products, PaymentType paymentType) {
        Ticket billingTicket = null;
        Collection<Ticket> childTickets = new HashSet<>();

        if (products.size() == ONE_PRODUCT) {
            billingTicket = new Ticket(account, products.parallelStream().findFirst().get(), paymentType);
        } else {
            switch (ticketIssuingType) {
                case ONE_TICKET_FOR_ALL_PRODUCTS:
                    int price = ZERO_PRICE;
                    for (Product product : products) {
                        price += product.getPrice();
                        Ticket ticket = new Ticket(account, product, paymentType);
                        ticket.setTicketKinship(TicketKinship.Child);
                        ticket.setType(product.getType());
                        childTickets.add(ticket);
                    }
                    billingTicket = new Ticket(account, price, paymentType);
                    billingTicket.setTicketKinship(TicketKinship.Parent);
                    break;
                case ONE_TICKET_PER_PRODUCT:
                    break;
                default:
                    throw new TicketCreationException("Unable to create ticket with creation type: " + ticketIssuingType);
            }
        }

        return new TicketOrder(billingTicket, childTickets);
    }

}
