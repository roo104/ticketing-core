package dk.unwire.ticketing.core.domain.ticket.state;

public enum TransitionType {

    INITIAL,
    OK,
    TICKET_ERROR,
    TICKET_CANCELLED,
    TICKET_EXPIRED,
    TICKET_ACTIVATED,
    TICKET_REFUNDED,
    TRANSACTION_ERROR
}
