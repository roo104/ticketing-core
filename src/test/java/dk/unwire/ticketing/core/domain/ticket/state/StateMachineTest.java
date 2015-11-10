package dk.unwire.ticketing.core.domain.ticket.state;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class StateMachineTest {

    @Test
    public void init_to_Ordered_Null() {
        // when
        CombinedState state = StateMachine.init();

        // then
        assertEquals(TicketStateType.TICKET_ORDER_REQUEST_RECEIVED.getState(), state.ticketState().getState());
        assertEquals(TransactionStateType.TRANSACTION_NULL.getState(), state.transactionState().getState());
    }

    @Test
    public void OrderRequestReceived_Null_to_Ordered_Null() {
        // given
        CombinedState currentState = new CombinedState(TransitionType.INITIAL, TicketStateType.TICKET_ORDER_REQUEST_RECEIVED, TransactionStateType.TRANSACTION_NULL);

        // when
        CombinedState state = StateMachine.transitionOk(currentState);

        // then
        assertEquals(TicketStateType.TICKET_ORDERED.getState(), state.ticketState().getState());
        assertEquals(TransactionStateType.TRANSACTION_NULL.getState(), state.transactionState().getState());
    }

    @Test
    public void Ordered_Null_to_Ordered_Initialized() {
        // given
        CombinedState currentState = new CombinedState(TransitionType.INITIAL, TicketStateType.TICKET_ORDERED, TransactionStateType.TRANSACTION_NULL);

        // when
        CombinedState state = StateMachine.transitionOk(currentState);

        // then
        assertEquals(TicketStateType.TICKET_ORDERED.getState(), state.ticketState().getState());
        assertEquals(TransactionStateType.TRANSACTION_INITIALIZED.getState(), state.transactionState().getState());
    }

    @Test
    public void Ordered_Initialized_to_Error_Initialized() {
        // given
        CombinedState currentState = new CombinedState(TransitionType.INITIAL, TicketStateType.TICKET_ORDERED, TransactionStateType.TRANSACTION_INITIALIZED);

        // when
        CombinedState state = StateMachine.transitionTicketError(currentState);

        // then
        assertEquals(TicketStateType.TICKET_ERROR.getState(), state.ticketState().getState());
        assertEquals(TransactionStateType.TRANSACTION_INITIALIZED.getState(), state.transactionState().getState());
    }

    @Test
    public void Error_Initialized_to_Error_Error() {
        // given
        CombinedState currentState = new CombinedState(TransitionType.INITIAL, TicketStateType.TICKET_ERROR, TransactionStateType.TRANSACTION_INITIALIZED);

        // when
        CombinedState state = StateMachine.transitionTicketError(currentState);

        // then
        assertEquals(TicketStateType.TICKET_ERROR.getState(), state.ticketState().getState());
        assertEquals(TransactionStateType.TRANSACTION_ERROR.getState(), state.transactionState().getState());
    }

    @Test
    public void Ordered_Initialized_to_Ordered_Error() {
        // given
        CombinedState currentState = new CombinedState(TransitionType.INITIAL, TicketStateType.TICKET_ORDERED, TransactionStateType.TRANSACTION_INITIALIZED);

        // when
        CombinedState state = StateMachine.transitionTransactionError(currentState);

        // then
        assertEquals(TicketStateType.TICKET_ORDERED.getState(), state.ticketState().getState());
        assertEquals(TransactionStateType.TRANSACTION_ERROR.getState(), state.transactionState().getState());
    }

    @Test
    public void Ordered_Error_to_Error_Error() {
        // given
        CombinedState currentState = new CombinedState(TransitionType.INITIAL, TicketStateType.TICKET_ORDERED, TransactionStateType.TRANSACTION_ERROR);

        // when
        CombinedState state = StateMachine.transitionTransactionError(currentState);

        // then
        assertEquals(TicketStateType.TICKET_ERROR.getState(), state.ticketState().getState());
        assertEquals(TransactionStateType.TRANSACTION_ERROR.getState(), state.transactionState().getState());
    }

    @Test
    public void Ordered_Initialized_to_Ordered_Reserved() {
        // given
        CombinedState currentState = new CombinedState(TransitionType.INITIAL, TicketStateType.TICKET_ORDERED, TransactionStateType.TRANSACTION_INITIALIZED);

        // when
        CombinedState state = StateMachine.transitionOk(currentState);

        // then
        assertEquals(TicketStateType.TICKET_ORDERED.getState(), state.ticketState().getState());
        assertEquals(TransactionStateType.TRANSACTION_RESERVED.getState(), state.transactionState().getState());
    }

    @Test
    public void Ordered_Reserved_to_Ordered_Error() {
        // given
        CombinedState currentState = new CombinedState(TransitionType.INITIAL, TicketStateType.TICKET_ORDERED, TransactionStateType.TRANSACTION_RESERVED);

        // when
        CombinedState state = StateMachine.transitionTransactionError(currentState);

        // then
        assertEquals(TicketStateType.TICKET_ORDERED.getState(), state.ticketState().getState());
        assertEquals(TransactionStateType.TRANSACTION_ERROR.getState(), state.transactionState().getState());
    }

    @Test
    public void Ordered_Reserved_to_Ordered_Billed() {
        // given
        CombinedState currentState = new CombinedState(TransitionType.INITIAL, TicketStateType.TICKET_ORDERED, TransactionStateType.TRANSACTION_RESERVED);

        // when
        CombinedState state = StateMachine.transitionOk(currentState);

        // then
        assertEquals(TicketStateType.TICKET_ORDERED.getState(), state.ticketState().getState());
        assertEquals(TransactionStateType.TRANSACTION_BILLED.getState(), state.transactionState().getState());
    }

    @Test
    public void Ordered_Billed_to_Cancelled_Billed() {
        // given
        CombinedState currentState = new CombinedState(TransitionType.INITIAL, TicketStateType.TICKET_ORDERED, TransactionStateType.TRANSACTION_BILLED);

        // when
        CombinedState state = StateMachine.transitionTicketCancelled(currentState);

        // then
        assertEquals(TicketStateType.TICKET_CANCELLED.getState(), state.ticketState().getState());
        assertEquals(TransactionStateType.TRANSACTION_BILLED.getState(), state.transactionState().getState());
    }

    @Test
    public void Ordered_Billed_to_Expired_Billed() {
        // given
        CombinedState currentState = new CombinedState(TransitionType.INITIAL, TicketStateType.TICKET_ORDERED, TransactionStateType.TRANSACTION_BILLED);

        // when
        CombinedState state = StateMachine.transitionTicketExpired(currentState);

        // then
        assertEquals(TicketStateType.TICKET_EXPIRED.getState(), state.ticketState().getState());
        assertEquals(TransactionStateType.TRANSACTION_BILLED.getState(), state.transactionState().getState());
    }

    @Test
    public void Ordered_Billed_to_Delivered_Billed() {
        // given
        CombinedState currentState = new CombinedState(TransitionType.INITIAL, TicketStateType.TICKET_ORDERED, TransactionStateType.TRANSACTION_BILLED);

        // when
        CombinedState state = StateMachine.transitionTicketActivated(currentState);

        // then
        assertEquals(TicketStateType.TICKET_DELIVERED.getState(), state.ticketState().getState());
        assertEquals(TransactionStateType.TRANSACTION_BILLED.getState(), state.transactionState().getState());
    }

    @Test
    public void Ordered_Billed_to_Returned_Billed() {
        // given
        CombinedState currentState = new CombinedState(TransitionType.INITIAL, TicketStateType.TICKET_ORDERED, TransactionStateType.TRANSACTION_BILLED);

        // when
        CombinedState state = StateMachine.transitionTicketRefunded(currentState);

        // then
        assertEquals(TicketStateType.TICKET_RETURNED.getState(), state.ticketState().getState());
        assertEquals(TransactionStateType.TRANSACTION_BILLED.getState(), state.transactionState().getState());
    }

    @Test
    public void Delivered_Billed_to_Returned_Billed() {
        // given
        CombinedState currentState = new CombinedState(TransitionType.INITIAL, TicketStateType.TICKET_DELIVERED, TransactionStateType.TRANSACTION_BILLED);

        // when
        CombinedState state = StateMachine.transitionTicketRefunded(currentState);

        // then
        assertEquals(TicketStateType.TICKET_RETURNED.getState(), state.ticketState().getState());
        assertEquals(TransactionStateType.TRANSACTION_BILLED.getState(), state.transactionState().getState());
    }

    @Test
    public void Delivered_Billed_to_Cancelled_Billed() {
        // given
        CombinedState currentState = new CombinedState(TransitionType.INITIAL, TicketStateType.TICKET_DELIVERED, TransactionStateType.TRANSACTION_BILLED);

        // when
        CombinedState state = StateMachine.transitionTicketCancelled(currentState);

        // then
        assertEquals(TicketStateType.TICKET_CANCELLED.getState(), state.ticketState().getState());
        assertEquals(TransactionStateType.TRANSACTION_BILLED.getState(), state.transactionState().getState());
    }

    @Test
    public void Expired_Billed_to_Delivered_Billed() {
        // given
        CombinedState currentState = new CombinedState(TransitionType.INITIAL, TicketStateType.TICKET_EXPIRED, TransactionStateType.TRANSACTION_BILLED);

        // when
        CombinedState state = StateMachine.transitionTicketActivated(currentState);

        // then
        assertEquals(TicketStateType.TICKET_DELIVERED.getState(), state.ticketState().getState());
        assertEquals(TransactionStateType.TRANSACTION_BILLED.getState(), state.transactionState().getState());
    }

    @Test
    public void Returned_Billed_to_Returned_Error() {
        // given
        CombinedState currentState = new CombinedState(TransitionType.INITIAL, TicketStateType.TICKET_RETURNED, TransactionStateType.TRANSACTION_BILLED);

        // when
        CombinedState state = StateMachine.transitionTransactionError(currentState);

        // then
        assertEquals(TicketStateType.TICKET_RETURNED.getState(), state.ticketState().getState());
        assertEquals(TransactionStateType.TRANSACTION_ERROR.getState(), state.transactionState().getState());
    }

    @Test
    public void Returned_Billed_to_Returned_Refunded() {
        // given
        CombinedState currentState = new CombinedState(TransitionType.INITIAL, TicketStateType.TICKET_RETURNED, TransactionStateType.TRANSACTION_BILLED);

        // when
        CombinedState state = StateMachine.transitionOk(currentState);

        // then
        assertEquals(TicketStateType.TICKET_RETURNED.getState(), state.ticketState().getState());
        assertEquals(TransactionStateType.TRANSACTION_REFUNDED.getState(), state.transactionState().getState());
    }
}