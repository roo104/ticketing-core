package dk.unwire.ticketing.core.domain.ticket.model.factory;

import dk.unwire.ticketing.core.domain.account.model.Account;
import dk.unwire.ticketing.core.domain.application.model.Application;
import dk.unwire.ticketing.core.domain.product.model.Product;
import dk.unwire.ticketing.core.domain.ticket.model.Ticket;
import dk.unwire.ticketing.core.domain.ticket.model.vo.PaymentType;
import dk.unwire.ticketing.core.domain.ticket.model.vo.TicketIssuingType;
import dk.unwire.ticketing.core.domain.ticket.model.vo.TicketKinship;
import dk.unwire.ticketing.core.domain.ticket.model.vo.TicketOrder;
import dk.unwire.ticketing.spring.rest.common.header.MticketIdentifierType;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.Collection;
import java.util.HashSet;

import static org.junit.Assert.assertEquals;

@RunWith(MockitoJUnitRunner.class)
public class TicketFactoryTest {

    @InjectMocks
    private TicketFactory ticketFactory;

    Account account;
    Collection<Product> products;
    Application application;
    Product product1;
    Product product2;

    @Before
    public void setup() {
        this.application = new Application();
        this.account = new Account(1, MticketIdentifierType.MSISDN, "4511111111");
        this.products = new HashSet<>();

        this.product1 = Product.newBuilder().withName("test product1").withType("adult").withApplication(this.application).withPrice(1000, "0.00").build();
        this.product2 = Product.newBuilder().withName("test product2").withType("adult").withApplication(this.application).withPrice(2000, "0.00").build();
    }

    @Test
    public void createSingleTicket_ONE_TICKET_FOR_ALL_PRODUCTS() throws Exception {
        // given a product
        this.products.add(this.product1);

        // when building a ticket
        TicketOrder ticketOrder = this.ticketFactory.createTickets(TicketIssuingType.ONE_TICKET_FOR_ALL_PRODUCTS, this.account, this.products, PaymentType.NO_PAYMENT);

        // then one ticket should be returned
        Ticket billingTicket = ticketOrder.getBillingTicket();
        assertEquals("Number of child tickets", 0, ticketOrder.getChildTickets().size());
        assertEquals("Product price", this.product1.getPrice(), billingTicket.getTicketPrice().getPrice().intValue());
        assertEquals("Kinship", TicketKinship.None, billingTicket.getTicketKinship());

    }

    @Test
    public void createMultipleTickets_ONE_TICKET_FOR_ALL_PRODUCTS() throws Exception {
        // given a product
        this.products.add(this.product1);
        this.products.add(this.product2);

        // when building a ticket
        TicketOrder ticketOrder = this.ticketFactory.createTickets(TicketIssuingType.ONE_TICKET_FOR_ALL_PRODUCTS, this.account, this.products, PaymentType.NO_PAYMENT);

        // then one ticket should be returned
        Ticket billingTicket = ticketOrder.getBillingTicket();
        assertEquals("Number of child tickets", 2, ticketOrder.getChildTickets().size());
        assertEquals("Product price", this.product1.getPrice() + this.product2.getPrice(), billingTicket.getTicketPrice().getPrice().intValue());
        assertEquals("ParentKinship", TicketKinship.Parent, billingTicket.getTicketKinship());
        for (Ticket childTicket : ticketOrder.getChildTickets()) {
            assertEquals("Child Kinship", TicketKinship.Child, childTicket.getTicketKinship());

        }

    }
}