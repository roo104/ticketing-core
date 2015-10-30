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
    @Getter
    @Column(name = "product_id")
    private int productId;
    @Getter
    @Column(name = "product_count")
    private int productCount;
}
