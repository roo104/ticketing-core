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

        // Transition [8,1] -> OK[8,4], AUTH_ERROR[8,16], TICKET_GENERATION_ERROR[16,1],
        startCombinedState = getCombinedState(TransitionType.INITIAL, TicketStateType.TICKET_ORDERED, TransactionStateType.TRANSACTION_INITIALIZED);
        endCombinedStates = getCombined(
                getCombinedState(TransitionType.OK, TicketStateType.TICKET_ORDERED, TransactionStateType.TRANSACTION_RESERVED),
                getCombinedState(TransitionType.TRANSACTION_ERROR, TicketStateType.TICKET_ORDERED, TransactionStateType.TRANSACTION_ERROR),
                getCombinedState(TransitionType.TICKET_ERROR, TicketStateType.TICKET_ERROR, TransactionStateType.TRANSACTION_RESERVED));
        ACCEPTED_CHANGES.put(startCombinedState, endCombinedStates);

        // Transition [8,16] -> OK[16,16]
        startCombinedState = getCombinedState(TransitionType.INITIAL, TicketStateType.TICKET_ORDERED, TransactionStateType.TRANSACTION_ERROR);
        endCombinedStates = getCombined(getCombinedState(TransitionType.TICKET_ERROR, TicketStateType.TICKET_ERROR, TransactionStateType.TRANSACTION_ERROR));
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


    public static CombinedState transitionOk(CombinedState currentState) {
        return nextState(currentState, TransitionType.OK);
    }

    public static CombinedState init() {
        return ACCEPTED_CHANGES.keySet().stream().findFirst().get();
    }

    public static CombinedState transitionTransactionError(CombinedState currentState) {
        return nextState(currentState, TransitionType.TRANSACTION_ERROR);
    }

    public static CombinedState transitionTicketError(CombinedState currentState) {
        return nextState(currentState, TransitionType.TICKET_ERROR);
    }

    private static CombinedState nextState(CombinedState currentState, TransitionType transitionType) {
        Set<CombinedState> transitions = ACCEPTED_CHANGES.get(currentState);
        CombinedState nextState = null;
        for (CombinedState transition : transitions) {
            if (transitionType == transition.transitionType()) {
                nextState = transition;
                break;
            }
        }
        if (nextState == null) {
            throw new IllegalStateException();
        }
        return nextState;
    }
}
