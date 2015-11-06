package dk.unwire.ticketing.core.domain.ticket.state;

import com.unwire.mticket.util.object.EqualsHelper;
import com.unwire.mticket.util.object.HashCodeHelper;

public final class CombinedState {

    private final TransitionType transitionType;
    private final TicketStateType ticketStateType;
    private final TransactionStateType transactionStateType;

    public CombinedState(TransitionType transitionType, TicketStateType ticketStateType, TransactionStateType transactionStateType) {
        this.transitionType = transitionType;
        this.ticketStateType = ticketStateType;
        this.transactionStateType = transactionStateType;
    }

    public TransitionType transitionType() {
        return this.transitionType;
    }

    public TicketStateType ticketState() {
        return this.ticketStateType;
    }

    public TransactionStateType transactionState() {
        return this.transactionStateType;
    }

    @Override
    public boolean equals(Object o) {
        boolean isEqual = false;
        if (this == o) {
            isEqual = true;
        }

        if (!isEqual && o instanceof CombinedState) {
            CombinedState that = (CombinedState) o;
            EqualsHelper helper = new EqualsHelper();
            helper.append(this.transitionType, that.transitionType);
            helper.append(this.ticketStateType, that.ticketStateType);
            helper.append(this.transactionStateType, that.transactionStateType);
            isEqual = helper.isEqual();
        }
        return isEqual;
    }

    @Override
    public int hashCode() {
        HashCodeHelper helper = new HashCodeHelper();
        helper.append(this.transitionType);
        helper.append(this.ticketStateType);
        helper.append(this.transactionStateType);
        return helper.hashCode();
    }
}
