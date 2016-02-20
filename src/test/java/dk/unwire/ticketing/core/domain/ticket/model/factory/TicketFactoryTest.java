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
    CreateTicketVO createTicketVO;

    @Before
    public void setup() {
        this.application = new Application();
        this.account = new Account(1, MticketIdentifierType.MSISDN, "4511111111");
        this.products = new HashSet<>();

        this.product1 = Product.newBuilder().withName("test product1").withType("adult").withApplication(this.application).withPrice(1000, "0.00").build();
        this.product2 = Product.newBuilder().withName("test product2").withType("adult").withApplication(this.application).withPrice(2000, "0.00").build();

        this.createTicketVO = CreateTicketVO.newBuilder()
                .ticketIssuingType(TicketIssuingType.ONE_TICKET_FOR_ALL_PRODUCTS)
                .products(this.products)
                .paymentType(PaymentType.NO_PAYMENT)
                .account(this.account).build();
    }

    @Test
    public void createSingleTicket_ONE_TICKET_FOR_ALL_PRODUCTS() throws Exception {
        // given a product
        this.products.add(this.product1);

        // when creating a TicketOrder with one product
        TicketOrder ticketOrder = this.ticketFactory.createTickets(this.createTicketVO);

        // then one billing- and no child tickets should be returned
        Ticket billingTicket = ticketOrder.getBillingTicket();
        assertEquals("Number of child tickets", 0, ticketOrder.getChildTickets().size());
        assertBillingTicket(billingTicket, this.product1.getPrice());

    }

    private void assertBillingTicket(Ticket billingTicket, int expectedPrice) {
        assertEquals("Product price", expectedPrice, billingTicket.getTicketPrice().getPrice().intValue());
        assertEquals("Kinship", TicketKinship.Parent, billingTicket.getTicketKinship());
        assertEquals("PaymentType", PaymentType.NO_PAYMENT, billingTicket.getTicketPrice().getPaymentType());
    }

    @Test
    public void createMultipleTickets_ONE_TICKET_FOR_ALL_PRODUCTS() throws Exception {
        // given a two products
        this.products.add(this.product1);
        this.products.add(this.product2);

        // when creating a TicketOrder with two products
        TicketOrder ticketOrder = this.ticketFactory.createTickets(this.createTicketVO);

        // then one billing- and one child ticket should be returned
        Ticket billingTicket = ticketOrder.getBillingTicket();
        assertEquals("Number of child tickets", 2, ticketOrder.getChildTickets().size());
        assertBillingTicket(billingTicket, this.product1.getPrice() + this.product2.getPrice());

        for (Ticket childTicket : ticketOrder.getChildTickets()) {
            assertEquals("Child Kinship", TicketKinship.Child, childTicket.getTicketKinship());

        }

    }
}