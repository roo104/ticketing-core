package dk.unwire.ticketing.core.domain.order.model;

import dk.unwire.ticketing.core.common.model.PropertyMap;
import dk.unwire.ticketing.core.domain.order.model.payment.Payment;
import lombok.Getter;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Table(name = "\"order\"")
@AssociationOverride(name = "properties", joinColumns = @JoinColumn(name = "order_id", referencedColumnName = "id"))
public class Order extends PropertyMap<OrderProperty> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    @Getter
    private long id;
    @Getter
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "order_id", referencedColumnName = "id")
    private Collection<Item> items;
    @Getter
    @Embedded
    private Payment payment;
    @Getter
    @Column(name = "note")
    private String note;
    @Getter
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "order_id", referencedColumnName = "id")
    private Collection<OrderLog> orderLogs;

}
