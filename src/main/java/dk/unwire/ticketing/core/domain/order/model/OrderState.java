package dk.unwire.ticketing.core.domain.order.model;

import javax.persistence.*;

@Entity
@Table(name = "order_state")
public class OrderState {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;
}
