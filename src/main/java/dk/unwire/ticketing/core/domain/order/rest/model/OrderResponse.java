package dk.unwire.ticketing.core.domain.order.rest.model;

import dk.unwire.ticketing.spring.rest.common.response.BaseResponse;
import dk.unwire.ticketing.spring.rest.common.response.GenericResponseInfo;
import lombok.Getter;

public class OrderResponse extends BaseResponse {

    @Getter
    private final long orderId;

    public OrderResponse(long orderId) {
        super(GenericResponseInfo.OK, "OK");
        this.orderId = orderId;
    }
}
