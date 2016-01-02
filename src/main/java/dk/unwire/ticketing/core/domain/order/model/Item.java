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
    private final long productId;
    @Getter
    @Column(name = "product_count")
    private final int productCount;

    private Item() {
        this.productId = 0;
        this.productCount = 0;

    }

    public Item(long productId, int productCount) {
        this.productId = productId;
        this.productCount = productCount;
    }
}
