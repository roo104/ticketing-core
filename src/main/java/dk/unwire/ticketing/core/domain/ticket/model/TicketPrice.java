package dk.unwire.ticketing.core.domain.ticket.model;

import lombok.Getter;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class TicketPrice {

    @Getter
    @Column(name = "price")
    private Integer price;
    @Getter
    @Column(name = "price_in_tokens")
    private Integer priceInTokens;
    @Getter
    @Column(name = "vat")
    private String vat;
    @Getter
    @Column(name = "payment_type_id")
    private Integer paymentTypeId;
}
