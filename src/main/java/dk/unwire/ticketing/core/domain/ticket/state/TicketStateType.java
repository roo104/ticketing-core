package dk.unwire.ticketing.core.domain.ticket.state;

public enum TicketStateType {

    /**
     * This is then the order has been received by the system.
     */
    TICKET_ORDER_REQUEST_RECEIVED(1),

    /**
     * This is then the order has been canceled.
     */
    TICKET_CANCELED(4),

    /**
     * This is then the order has been ordered.
     */
    TICKET_ORDERED(8),

    /**
     * This is then the ticket is in Error.
     */
    TICKET_ERROR(16),

    /**
     * This is then the ticket has been delivered.
     */
    TICKET_DELIVERED(32),

    /**
     * This is then the ticket has been returned.
     */
    TICKET_RETURNED(64),

    /**
     * When the ticket is expired due to max days criteria without being activated
     */
    TICKET_EXPIRED(256);

    private int state;

    TicketStateType(int state) {
        this.state = state;
    }

    public int getState() {
        return this.state;
    }

    public static TicketStateType fromValue(int state) {
        TicketStateType ticketStateType = TICKET_ORDER_REQUEST_RECEIVED;
        for (TicketStateType internalState : TicketStateType.values()) {
            if (state == internalState.getState()) {
                ticketStateType = internalState;
                break;
            }
        }
        return ticketStateType;
    }
}
