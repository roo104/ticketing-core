package dk.unwire.ticketing.core.domain.ticket.state;

public enum TransactionStateType {

    /**
     * This is then the transaction is not started.
     */
    TRANSACTION_NULL(0),

    /**
     * This is then the transaction is initialized.
     */
    TRANSACTION_INITIALIZED(1),

    /**
     * This is then the transaction is billed.
     */
    TRANSACTION_BILLED(2),

    /**
     * This is then the transaction is Reserved.
     */
    TRANSACTION_RESERVED(4),

    /**
     * This is then the transaction is in error.
     */
    TRANSACTION_ERROR(16),

    /**
     * This is then the transaction is refunded.
     */
    TRANSACTION_REFUNDED(64);

    private int state;

    TransactionStateType(int state) {
        this.state = state;
    }

    public int getState() {
        return this.state;
    }

    public static TransactionStateType fromValue(int state) {
        TransactionStateType ticketState = TRANSACTION_NULL;
        for (TransactionStateType internalState : TransactionStateType.values()) {
            if (state == internalState.getState()) {
                ticketState = internalState;
                break;
            }
        }
        return ticketState;
    }
}
