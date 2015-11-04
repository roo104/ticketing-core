package dk.unwire.ticketing.core.domain.order.repository;

import dk.unwire.ticketing.core.TicketingCoreApplication;
import dk.unwire.ticketing.core.domain.order.model.Order;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = TicketingCoreApplication.class)
@WebAppConfiguration
@Transactional
public class OrderRepositoryIT {

    @Autowired
    private OrderRepository orderRepository;

    @Test
    public void findOne() {
        Order order = this.orderRepository.findOne(1L);
        assertNotNull(order);
        assertNotNull(order.getNote());
        assertEquals(1, order.getItems().size());
        assertEquals(1, order.getOrderLogs().size());
        assertNotNull(order.getPayment());
    }
}