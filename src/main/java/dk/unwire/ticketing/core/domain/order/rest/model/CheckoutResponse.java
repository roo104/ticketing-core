package dk.unwire.ticketing.core.domain.order.rest.model;

import lombok.Getter;

public class CheckoutResponse {

    @Getter
    private final int totalAmount;
    @Getter
    private final String redirectUrl;

    public CheckoutResponse(int totalAmount, String redirectUrl) {
        this.totalAmount = totalAmount;
        this.redirectUrl = redirectUrl;
    }
}
