package dk.unwire.ticketing.core.domain.order.model;

import dk.unwire.ticketing.core.domain.order.model.state.OrderState;
import lombok.Getter;

import javax.persistence.*;
import java.time.ZonedDateTime;

@Entity
@Table(name = "order_state")
public class OrderLog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;
    @Getter
    @Column(name = "state")
    private OrderState state;
    @Getter
    @Column(name = "timestamp")
    private ZonedDateTime timestamp;
}
