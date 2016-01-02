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
    @JoinColumn(name = "id", referencedColumnName = "id")
    private Collection<Item> items;
    @Getter
    @Embedded
    private Payment payment;
    @Getter
    @Column(name = "note")
    private String note;
    @Getter
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "id", referencedColumnName = "id")
    private Collection<OrderLog> orderLogs;

    public String getRedirectUrl() {
        return null;
    }

    public int getTotalAmount() {
        return 0;
    }

    public void replaceItems(Collection<Item> items) {
        this.items = items;
    }

    public void replacePayment(Payment payment) {
        this.payment = payment;
    }

    public void updateNote(String note) {
        this.note = note;
    }
}
