package dk.unwire.ticketing.core.domain.order.model;

import dk.unwire.ticketing.core.domain.order.model.state.OrderState;
import lombok.Getter;

import javax.persistence.*;

@Entity
@Table(name = "order_log")
public class OrderLog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;
    @Getter
    @Column(name = "state")
    private OrderState state;
}
