package dk.unwire.ticketing.core.domain.ticket.model.factory;

import dk.unwire.ticketing.core.domain.account.model.Account;
import dk.unwire.ticketing.core.domain.product.model.Product;
import dk.unwire.ticketing.core.domain.ticket.model.Ticket;
import dk.unwire.ticketing.core.domain.ticket.model.vo.PaymentType;
import dk.unwire.ticketing.core.domain.ticket.model.vo.TicketIssuingType;
import dk.unwire.ticketing.core.domain.ticket.model.vo.TicketOrder;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.HashSet;

@Component
public class TicketFactory {

    private static final int ONE_PRODUCT = 1;
    private static final int ZERO_PRICE = 0;
    private static final boolean IS_BILLING_TICKET = true;
    private static final boolean IS_NO_BILLING_TICKET = false;

    public TicketOrder createTickets(CreateTicketVO createTicketVO) {
        Ticket billingTicket;
        Collection<Ticket> childTickets = new HashSet<>();

        Collection<Product> products = createTicketVO.getProducts();
        Account account = createTicketVO.getAccount();
        PaymentType paymentType = createTicketVO.getPaymentType();
        TicketIssuingType ticketIssuingType = createTicketVO.getTicketIssuingType();

        if (products.size() == ONE_PRODUCT) {
            billingTicket = new Ticket(account, products.parallelStream().findFirst().get(), paymentType);
            billingTicket.setTicketKinship(ticketIssuingType, IS_BILLING_TICKET);
        } else {
            int price = ZERO_PRICE;

            for (Product product : products) {
                price += product.getPrice();
                Ticket ticket = new Ticket(account, product, paymentType);
                ticket.setTicketKinship(ticketIssuingType, IS_NO_BILLING_TICKET);
                childTickets.add(ticket);
            }

            billingTicket = new Ticket(account, price, paymentType);
            billingTicket.setTicketKinship(ticketIssuingType, IS_BILLING_TICKET);
        }

        return new TicketOrder(billingTicket, childTickets);
    }
}
