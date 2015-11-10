package dk.unwire.ticketing.core.domain.ticket.state;

import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

public class StateMachine {

    private static final Map<CombinedState, Set<CombinedState>> ACCEPTED_CHANGES;

    static {
        ACCEPTED_CHANGES = new LinkedHashMap<>();

        // Transition [1,0] -> OK[8,0]
        CombinedState startCombinedState = getCombinedState(TransitionType.INITIAL, TicketStateType.TICKET_ORDER_REQUEST_RECEIVED, TransactionStateType.TRANSACTION_NULL);
        Set<CombinedState> endCombinedStates = getCombined(getCombinedState(TransitionType.OK, TicketStateType.TICKET_ORDERED, TransactionStateType.TRANSACTION_NULL));
        ACCEPTED_CHANGES.put(startCombinedState, endCombinedStates);

        // Transition [8,0] -> OK[8,1]
        startCombinedState = getCombinedState(TransitionType.INITIAL, TicketStateType.TICKET_ORDERED, TransactionStateType.TRANSACTION_NULL);
        endCombinedStates = getCombined(getCombinedState(TransitionType.OK, TicketStateType.TICKET_ORDERED, TransactionStateType.TRANSACTION_INITIALIZED));
        ACCEPTED_CHANGES.put(startCombinedState, endCombinedStates);

        // Transition [8,1] -> OK[8,4], TRANSACTION_ERROR[8,16], TICKET_GENERATION_ERROR[16,1]
        startCombinedState = getCombinedState(TransitionType.INITIAL, TicketStateType.TICKET_ORDERED, TransactionStateType.TRANSACTION_INITIALIZED);
        endCombinedStates = getCombined(
                getCombinedState(TransitionType.OK, TicketStateType.TICKET_ORDERED, TransactionStateType.TRANSACTION_RESERVED),
                getCombinedState(TransitionType.TRANSACTION_ERROR, TicketStateType.TICKET_ORDERED, TransactionStateType.TRANSACTION_ERROR),
                getCombinedState(TransitionType.TICKET_ERROR, TicketStateType.TICKET_ERROR, TransactionStateType.TRANSACTION_INITIALIZED));
        ACCEPTED_CHANGES.put(startCombinedState, endCombinedStates);

        // Transition [16,1] -> TICKET_GENERATION_ERROR[16,16]
        startCombinedState = getCombinedState(TransitionType.INITIAL, TicketStateType.TICKET_ERROR, TransactionStateType.TRANSACTION_INITIALIZED);
        endCombinedStates = getCombined(getCombinedState(TransitionType.TICKET_ERROR, TicketStateType.TICKET_ERROR, TransactionStateType.TRANSACTION_ERROR));
        ACCEPTED_CHANGES.put(startCombinedState, endCombinedStates);

        // Transition [8,4] -> OK[8,2], CAPTURE_ERROR[8,16]
        startCombinedState = getCombinedState(TransitionType.INITIAL, TicketStateType.TICKET_ORDERED, TransactionStateType.TRANSACTION_RESERVED);
        endCombinedStates = getCombined(
                getCombinedState(TransitionType.OK, TicketStateType.TICKET_ORDERED, TransactionStateType.TRANSACTION_BILLED),
                getCombinedState(TransitionType.TRANSACTION_ERROR, TicketStateType.TICKET_ORDERED, TransactionStateType.TRANSACTION_ERROR));
        ACCEPTED_CHANGES.put(startCombinedState, endCombinedStates);

        // Transition [8,16] -> TRANSACTION_ERROR[16,16]
        startCombinedState = getCombinedState(TransitionType.INITIAL, TicketStateType.TICKET_ORDERED, TransactionStateType.TRANSACTION_ERROR);
        endCombinedStates = getCombined(getCombinedState(TransitionType.TRANSACTION_ERROR, TicketStateType.TICKET_ERROR, TransactionStateType.TRANSACTION_ERROR));
        ACCEPTED_CHANGES.put(startCombinedState, endCombinedStates);

        // Transition [8,2] -> TICKET_CANCELLED[4,2], TICKET_EXPIRED[256,2], TICKET_ACTIVATED[32,2], TICKET_REFUNDED[64,2]
        startCombinedState = getCombinedState(TransitionType.INITIAL, TicketStateType.TICKET_ORDERED, TransactionStateType.TRANSACTION_BILLED);
        endCombinedStates = getCombined(
                getCombinedState(TransitionType.TICKET_CANCELLED, TicketStateType.TICKET_CANCELLED, TransactionStateType.TRANSACTION_BILLED),
                getCombinedState(TransitionType.TICKET_EXPIRED, TicketStateType.TICKET_EXPIRED, TransactionStateType.TRANSACTION_BILLED),
                getCombinedState(TransitionType.TICKET_ACTIVATED, TicketStateType.TICKET_DELIVERED, TransactionStateType.TRANSACTION_BILLED),
                getCombinedState(TransitionType.TICKET_REFUNDED, TicketStateType.TICKET_RETURNED, TransactionStateType.TRANSACTION_BILLED));
        ACCEPTED_CHANGES.put(startCombinedState, endCombinedStates);

        // Transition [256,2] -> TICKET_ACTIVATED[32,2]
        startCombinedState = getCombinedState(TransitionType.INITIAL, TicketStateType.TICKET_EXPIRED, TransactionStateType.TRANSACTION_BILLED);
        endCombinedStates = getCombined(getCombinedState(TransitionType.TICKET_ACTIVATED, TicketStateType.TICKET_DELIVERED, TransactionStateType.TRANSACTION_BILLED));
        ACCEPTED_CHANGES.put(startCombinedState, endCombinedStates);

        // Transition [32,2] -> TICKET_CANCELLED[4,2], TICKET_REFUNDED[64,2]
        startCombinedState = getCombinedState(TransitionType.INITIAL, TicketStateType.TICKET_DELIVERED, TransactionStateType.TRANSACTION_BILLED);
        endCombinedStates = getCombined(
                getCombinedState(TransitionType.TICKET_CANCELLED, TicketStateType.TICKET_CANCELLED, TransactionStateType.TRANSACTION_BILLED),
                getCombinedState(TransitionType.TICKET_REFUNDED, TicketStateType.TICKET_RETURNED, TransactionStateType.TRANSACTION_BILLED));
        ACCEPTED_CHANGES.put(startCombinedState, endCombinedStates);

        // Transition [64,2] -> TICKET_REFUNDED_FAILED[64,16], TICKET_REFUNDED[64,2]
        startCombinedState = getCombinedState(TransitionType.INITIAL, TicketStateType.TICKET_RETURNED, TransactionStateType.TRANSACTION_BILLED);
        endCombinedStates = getCombined(
                getCombinedState(TransitionType.TRANSACTION_ERROR, TicketStateType.TICKET_RETURNED, TransactionStateType.TRANSACTION_ERROR),
                getCombinedState(TransitionType.OK, TicketStateType.TICKET_RETURNED, TransactionStateType.TRANSACTION_REFUNDED));
        ACCEPTED_CHANGES.put(startCombinedState, endCombinedStates);
    }

    private static CombinedState getCombinedState(TransitionType transitionType, TicketStateType ticketStateType, TransactionStateType transactionStateType) {
        return new CombinedState(transitionType, ticketStateType, transactionStateType);
    }

    private static Set<CombinedState> getCombined(CombinedState... combinedStates) {
        Set<CombinedState> combinedStatesSet = new HashSet<CombinedState>();
        for (CombinedState combinedState : combinedStates) {
            combinedStatesSet.add(combinedState);
        }
        return combinedStatesSet;
    }

    public static CombinedState init() {
        return ACCEPTED_CHANGES.keySet().stream().findFirst().get();
    }

    public static CombinedState transitionOk(CombinedState currentState) {
        return nextState(currentState, TransitionType.OK);
    }

    public static CombinedState transitionTransactionError(CombinedState currentState) {
        return nextState(currentState, TransitionType.TRANSACTION_ERROR);
    }

    public static CombinedState transitionTicketError(CombinedState currentState) {
        return nextState(currentState, TransitionType.TICKET_ERROR);
    }

    public static CombinedState transitionTicketCancelled(CombinedState currentState) {
        return nextState(currentState, TransitionType.TICKET_CANCELLED);
    }

    public static CombinedState transitionTicketExpired(CombinedState currentState) {
        return nextState(currentState, TransitionType.TICKET_EXPIRED);
    }

    public static CombinedState transitionTicketActivated(CombinedState currentState) {
        return nextState(currentState, TransitionType.TICKET_ACTIVATED);
    }

    public static CombinedState transitionTicketRefunded(CombinedState currentState) {
        return nextState(currentState, TransitionType.TICKET_REFUNDED);
    }

    private static CombinedState nextState(CombinedState currentState, TransitionType transitionType) {
        Set<CombinedState> transitions = ACCEPTED_CHANGES.get(currentState);
        CombinedState nextState = null;
        if (transitions != null) {
            for (CombinedState transition : transitions) {
                if (transitionType == transition.transitionType()) {
                    nextState = transition;
                    break;
                }
            }
        }
        if (nextState == null) {
            throw new IllegalStateException(String.format("CurrentState [%s, %s] with transition [%s] not allowed", currentState.ticketState(), currentState.transactionState(), transitionType.name()));
        }
        return nextState;
    }
}
