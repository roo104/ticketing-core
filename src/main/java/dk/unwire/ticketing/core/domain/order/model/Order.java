package dk.unwire.ticketing.core.domain.order.model;

import dk.unwire.ticketing.core.common.model.PropertyMap;
import dk.unwire.ticketing.core.domain.order.model.payment.Payment;
import lombok.Getter;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Table(name = "order")
@AssociationOverride(name = "properties", joinColumns = @JoinColumn(name = "order_id", referencedColumnName = "id"))
public class Order extends PropertyMap<OrderProperty> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    @Getter
    private long id;
    @OneToMany(fetch = FetchType.LAZY)
    private Collection<Item> items;
    @Embedded
    private Payment payment;
    @Column(name = "note")
    private String note;
    @OneToMany(fetch = FetchType.LAZY)
    private Collection<OrderState> orderStates;

}
