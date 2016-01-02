package dk.unwire.ticketing.core.domain.order.rest.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import dk.unwire.ticketing.core.domain.order.model.Order;
import dk.unwire.ticketing.spring.rest.common.response.BaseResponse;
import dk.unwire.ticketing.spring.rest.common.response.GenericResponseInfo;

public class CompleteOrderResponse extends BaseResponse {

    @JsonProperty("checkout")
    private CheckoutResponse checkout;

    public CompleteOrderResponse(Order order) {
        super(GenericResponseInfo.OK, "OK");
        this.checkout = new CheckoutResponse(order.getTotalAmount(), order.getRedirectUrl());
    }
}
