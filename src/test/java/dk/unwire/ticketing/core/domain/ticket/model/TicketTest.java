package dk.unwire.ticketing.core.domain.ticket.model;

import dk.unwire.ticketing.core.domain.account.model.Account;
import dk.unwire.ticketing.core.domain.account.model.FindOrCreateAccountVO;
import dk.unwire.ticketing.core.domain.product.model.Product;
import dk.unwire.ticketing.core.domain.ticket.model.factory.TicketFactory;
import dk.unwire.ticketing.core.domain.ticket.state.TicketStateType;
import dk.unwire.ticketing.core.domain.ticket.state.TransactionStateType;
import dk.unwire.ticketing.spring.rest.common.header.MticketIdentifierType;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class TicketTest {

    @Autowired
    private TicketFactory ticketFactory;

    private Account account;

    @Before
    public void setup() {
        this.ticketFactory = new TicketFactory();

        FindOrCreateAccountVO findOrCreateAccountVO = new FindOrCreateAccountVO(1, "4511111111",
				MticketIdentifierType.MSISDN);
        this.account = Account.findOrCreateAccount(findOrCreateAccountVO);
    }

    /**
     * Ticket is created, but AUTH fails and ticket moves to error state.
     */
    @Test
    public void stateFromInitWithAuthFailToError() {
        // given a newly created ticket in initial state
        Ticket ticket = this.ticketFactory.buildTicket(this.account, new Product());
        assertTicketInState(ticket, TicketStateType.TICKET_ORDER_REQUEST_RECEIVED, TransactionStateType.TRANSACTION_NULL, 1);

        // when/then ticket moves from state [1,0] -> [8,0] -> [8,1] -> [8,16] -> [16,16]
        // [1,0] -> [8,0]
        ticket.nextStateOk();
        assertTicketInState(ticket, TicketStateType.TICKET_ORDERED, TransactionStateType.TRANSACTION_NULL, 2);

        // [8,0] -> [8,1]
        ticket.nextStateOk();
        assertTicketInState(ticket, TicketStateType.TICKET_ORDERED, TransactionStateType.TRANSACTION_INITIALIZED, 3);

        // [8,1] -> [8,16]
        ticket.nextStateTransactionError(100);
        assertTicketInState(ticket, TicketStateType.TICKET_ORDERED, TransactionStateType.TRANSACTION_ERROR, 4);

        // [8,16] -> [16,16]
        ticket.nextStateTransactionError(200);
        assertTicketInState(ticket, TicketStateType.TICKET_ERROR, TransactionStateType.TRANSACTION_ERROR, 5);
    }

    @Test
    public void refundTicket() {
        // given ticket in state [8,2]
        Ticket ticket = givenTicketInOrderedBilled();

        // when
        boolean refundTicket = ticket.refundTicket();

        // then
        assertTrue(refundTicket);
        assertTicketInState(ticket, TicketStateType.TICKET_RETURNED, TransactionStateType.TRANSACTION_BILLED, 6);
    }

    @Test
    public void activateTicket() {
        // given ticket in state [8,2]
        Ticket ticket = givenTicketInOrderedBilled();

        // when
        boolean activateTicket = ticket.activateTicket();

        // then
        assertTrue(activateTicket);
        assertTicketInState(ticket, TicketStateType.TICKET_DELIVERED, TransactionStateType.TRANSACTION_BILLED, 6);
    }

    @Test
    public void cancelTicket() {
        // given ticket in state [8,2]
        Ticket ticket = givenTicketInOrderedBilled();

        // when
        boolean activateTicket = ticket.cancelTicket();

        // then
        assertTrue(activateTicket);
        assertTicketInState(ticket, TicketStateType.TICKET_CANCELLED, TransactionStateType.TRANSACTION_BILLED, 6);
    }

    private void assertTicketInState(Ticket ticket, TicketStateType ticketStateType, TransactionStateType transactionStateType, int numberOfLogsAndStates) {
        assertEquals(ticketStateType.getState(), ticket.getTicketStateInfo().getLatestTicketState().getTicketState());
        assertEquals(transactionStateType.getState(), ticket.getTicketStateInfo().getLatestTicketState().getTransactionState());
        assertEquals(numberOfLogsAndStates, ticket.getTicketStateInfo().getTicketStates().size());
        assertEquals(numberOfLogsAndStates, ticket.getTicketStateInfo().getLogEntries().size());
    }

    private Ticket givenTicketInOrderedBilled() {
        // given ticket in state [8,2]
        Ticket ticket = this.ticketFactory.buildTicket(this.account, new Product());
        // [1,0] -> [8,0]
        ticket.nextStateOk();
        // [8,0] -> [8,1]
        ticket.nextStateOk();
        // [8,1] -> [8,4]
        ticket.nextStateOk();
        // [8,4] -> [8,2]
        ticket.nextStateOk();
        return ticket;
    }

}