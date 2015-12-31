package dk.unwire.ticketing.core.domain.order.rest;

import dk.unwire.ticketing.core.domain.order.rest.model.OrderRequest;
import dk.unwire.ticketing.core.domain.order.rest.model.OrderResponse;
import dk.unwire.ticketing.spring.rest.common.response.GenericResponseInfo;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/{applicationId}/order")
public class OrderController {

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<OrderResponse> createOrder(@PathVariable int applicationId) {
        return new ResponseEntity<>(new OrderResponse(1L), GenericResponseInfo.CREATED.getHttpStatus());
    }

    @RequestMapping(method = RequestMethod.PUT)
    public ResponseEntity<OrderResponse> updateOrder(@PathVariable int applicationId, @RequestBody OrderRequest orderRequest) {
        return new ResponseEntity<>(new OrderResponse(1L), GenericResponseInfo.OK.getHttpStatus());
    }
}
