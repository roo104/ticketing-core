package dk.unwire.ticketing.core.domain.order.application;

import dk.unwire.ticketing.core.domain.order.model.Order;
import dk.unwire.ticketing.core.domain.order.model.vo.UpdateOrderVO;
import dk.unwire.ticketing.core.domain.order.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class OrderApplication {

    @Autowired
    private OrderService orderService;

    public Order createEmptyOrder() {
        return this.orderService.createEmptyOrder();
    }

    public Order updateOrder(UpdateOrderVO updateOrderVO) {
        return this.orderService.updateOrder(updateOrderVO);
    }
}
