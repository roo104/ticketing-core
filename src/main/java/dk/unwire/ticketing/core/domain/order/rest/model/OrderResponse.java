package dk.unwire.ticketing.core.domain.order.rest.model;

import dk.unwire.ticketing.core.domain.order.model.Order;
import dk.unwire.ticketing.spring.rest.common.response.BaseResponse;
import dk.unwire.ticketing.spring.rest.common.response.GenericResponseInfo;
import lombok.Getter;

public class OrderResponse extends BaseResponse {

    @Getter
    private final long orderId;
    @Getter
    private final String note;

    public OrderResponse(Order order) {
        super(GenericResponseInfo.OK, "OK");
        this.orderId = order.getId();
        this.note = order.getNote();
    }
}
