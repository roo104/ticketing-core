package dk.unwire.ticketing.core.domain.ticket.model.vo;

public enum TicketIssuingType {

    /**
     * If there is only one product, ticket will be a ticket who holds both transaction and ticket info.
     * If there is more than one product, there will be a parent ticket holding transaction and one ticket for each product.
     */
    ONE_TICKET_PER_PRODUCT,

    /**
     * If there is only one product, ticket will one ticket who holds both transaction and ticket info.
     * If there is more than one product, there will be;
     * one child ticket for each product who only holds basic info.
     * One parent ticket holding transaction and ticket info.
     */
    ONE_TICKET_FOR_ALL_PRODUCTS
}
