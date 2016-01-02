package dk.unwire.ticketing.core.domain.order.service;

import dk.unwire.ticketing.core.domain.order.model.Order;
import dk.unwire.ticketing.core.domain.order.model.vo.UpdateOrderVO;
import dk.unwire.ticketing.core.domain.order.repository.OrderRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderService {

    private static final Logger logger = LoggerFactory.getLogger(OrderService.class);

    @Autowired
    private OrderRepository orderRepository;

    public Order createEmptyOrder() {
        Order order = new Order();
        this.orderRepository.save(order);
        logger.info("orderId: [{}] Created new order", order.getId());
        return order;
    }

    public Order updateOrder(UpdateOrderVO updateOrderVO) {
        Order order = this.orderRepository.findOne(updateOrderVO.getOrderId());
        order.replaceItems(updateOrderVO.getItems());
        order.replacePayment(updateOrderVO.getPayment());
        order.updateNote(updateOrderVO.getNote());
        this.orderRepository.save(order);
        logger.info("orderId: [{}] Updated order", order.getId());
        return order;
    }

    public Order getOrder(long orderId) {
        return this.orderRepository.findOne(orderId);
    }
}
