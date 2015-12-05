package dk.unwire.ticketing.core.domain.ticket.model;

import dk.unwire.ticketing.core.domain.ticket.model.vo.PaymentType;
import lombok.Getter;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class TicketPrice {

    @Getter
    @Column(name = "price")
    private final Integer price;
    @Getter
    @Column(name = "price_in_tokens")
    private final Integer priceInTokens;
    @Getter
    @Column(name = "vat")
    private final String vat;
    @Getter
    @Column(name = "payment_type_id")
    private final PaymentType paymentType;

    private TicketPrice() {
        this.price = 0;
        this.priceInTokens = 0;
        this.vat = "0.00";
        this.paymentType = null;
    }

    public TicketPrice(Integer price, PaymentType paymentType) {
        this.price = price;
        this.priceInTokens = 0;
        this.vat = "0.00";
        this.paymentType = paymentType;
    }
}
