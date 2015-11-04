package dk.unwire.ticketing.core.domain.order.repository;

import dk.unwire.ticketing.core.domain.order.model.Order;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends CrudRepository<Order, Long> {

}
