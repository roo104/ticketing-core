package dk.unwire.ticketing.core.domain.order.rest;

import dk.unwire.ticketing.core.domain.order.application.OrderApplication;
import dk.unwire.ticketing.core.domain.order.model.Order;
import dk.unwire.ticketing.core.domain.order.model.vo.UpdateOrderVO;
import dk.unwire.ticketing.core.domain.order.rest.model.CompleteOrderResponse;
import dk.unwire.ticketing.core.domain.order.rest.model.OrderResponse;
import dk.unwire.ticketing.core.domain.order.rest.model.UpdateOrderRequest;
import dk.unwire.ticketing.spring.rest.common.response.GenericResponseInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/order")
public class OrderController {

    @Autowired
    private OrderApplication orderApplication;

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<OrderResponse> createEmptyOrder() {
        Order order = this.orderApplication.createEmptyOrder();
        return new ResponseEntity<>(new OrderResponse(order), GenericResponseInfo.CREATED.getHttpStatus());
    }

    @RequestMapping(method = RequestMethod.PUT, path = "/{orderId}")
    public ResponseEntity<CompleteOrderResponse> updateAndCheckoutOrder(@PathVariable long orderId, @RequestBody UpdateOrderRequest orderRequest) {
        UpdateOrderVO updateOrderVO = orderRequest.toUpdateOrderVO(orderId);
        Order order = this.orderApplication.updateOrder(updateOrderVO);

        return new ResponseEntity<>(new CompleteOrderResponse(order), GenericResponseInfo.OK.getHttpStatus());
    }
}
