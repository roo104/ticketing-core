package dk.unwire.ticketing.core.domain.ticket.state;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

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
            EqualsBuilder helper = new EqualsBuilder();
            helper.append(this.transitionType, that.transitionType);
            helper.append(this.ticketStateType, that.ticketStateType);
            helper.append(this.transactionStateType, that.transactionStateType);
            isEqual = helper.isEquals();
        }
        return isEqual;
    }

    @Override
    public int hashCode() {
        HashCodeBuilder helper = new HashCodeBuilder();
        helper.append(this.transitionType);
        helper.append(this.ticketStateType);
        helper.append(this.transactionStateType);
        return helper.toHashCode();
    }
}
