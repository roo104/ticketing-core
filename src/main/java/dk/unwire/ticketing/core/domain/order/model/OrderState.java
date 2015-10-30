package dk.unwire.ticketing.core.domain.order.model;

import lombok.Getter;

import javax.persistence.*;

@Entity
@Table(name = "order_state")
public class OrderState {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;
    @Getter
    @Column(name = "state")
    private int state;
}
