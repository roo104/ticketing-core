package dk.unwire.ticketing.core.domain.ticket.model;

import dk.unwire.ticketing.core.domain.account.enums.IdentifierType;
import dk.unwire.ticketing.core.domain.account.model.Account;
import dk.unwire.ticketing.core.domain.account.model.FindOrCreateAccountVO;
import dk.unwire.ticketing.core.domain.product.model.Product;
import dk.unwire.ticketing.core.domain.ticket.model.factory.TicketFactory;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class TicketTest {

    @Autowired
    private TicketFactory ticketFactory;

    private Account account;

    @Before
    public void setup() {
        this.ticketFactory = new TicketFactory();

        FindOrCreateAccountVO findOrCreateAccountVO = new FindOrCreateAccountVO(1, "4511111111", IdentifierType.MSISDN);
        this.account = Account.findOrCreateAccount(findOrCreateAccountVO);
    }

    /**
     * Ticket is created, but AUTH fails and ticket moves to error state.
     */
    @Test
    public void stateFromInitWithAuthFailToError() {
        // given a newly created ticket in initial state
        Ticket ticket = this.ticketFactory.buildTicket(this.account, new Product());

        // when/then ticket moves from state [1,0] -> [8,0] -> [8,1] -> [8,16] -> [16,16]
        assertNotNull(ticket.getTicketStateInfo());
        assertNotNull(ticket.getTicketStateInfo().getLatestTicketState());
        assertEquals(1, ticket.getTicketStateInfo().getLatestTicketState().getTicketState());
        assertEquals(0, ticket.getTicketStateInfo().getLatestTicketState().getTransactionState());
        assertEquals(1, ticket.getTicketStateInfo().getTicketStates().size());
        assertEquals(1, ticket.getTicketStateInfo().getLogEntries().size());

        // [1,0] -> [8,0]
        ticket.nextStateOk();

        assertEquals(8, ticket.getTicketStateInfo().getLatestTicketState().getTicketState());
        assertEquals(0, ticket.getTicketStateInfo().getLatestTicketState().getTransactionState());
        assertEquals(2, ticket.getTicketStateInfo().getTicketStates().size());
        assertEquals(2, ticket.getTicketStateInfo().getLogEntries().size());

        // [8,0] -> [8,1]
        ticket.nextStateOk();

        assertEquals(8, ticket.getTicketStateInfo().getLatestTicketState().getTicketState());
        assertEquals(1, ticket.getTicketStateInfo().getLatestTicketState().getTransactionState());
        assertEquals(3, ticket.getTicketStateInfo().getTicketStates().size());
        assertEquals(3, ticket.getTicketStateInfo().getLogEntries().size());

        // [8,1] -> [8,16]
        ticket.nextStateTransactionError(100);

        assertEquals(8, ticket.getTicketStateInfo().getLatestTicketState().getTicketState());
        assertEquals(16, ticket.getTicketStateInfo().getLatestTicketState().getTransactionState());
        assertEquals(4, ticket.getTicketStateInfo().getTicketStates().size());
        assertEquals(4, ticket.getTicketStateInfo().getLogEntries().size());

        // [8,16] -> [16,16]
        ticket.nextStateTransactionError(200);

        assertEquals(16, ticket.getTicketStateInfo().getLatestTicketState().getTicketState());
        assertEquals(16, ticket.getTicketStateInfo().getLatestTicketState().getTransactionState());
        assertEquals(5, ticket.getTicketStateInfo().getTicketStates().size());
        assertEquals(5, ticket.getTicketStateInfo().getLogEntries().size());

    }

}