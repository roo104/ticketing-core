package dk.unwire.ticketing.core.domain.order.model;

import lombok.Getter;

import javax.persistence.*;

@Entity
@Table(name = "order_item")
public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    @Getter
    private long id;
    @Column(name = "product_id")
    private int productId;
    @Column(name = "product_count")
    private int productCount;
}
