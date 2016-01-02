package dk.unwire.ticketing.core.domain.order.model.vo;

import dk.unwire.ticketing.core.domain.order.model.Item;
import dk.unwire.ticketing.core.domain.order.model.payment.Payment;
import lombok.Getter;

import java.util.Collection;

@Getter
public class UpdateOrderVO {

    private final long orderId;
    private final Collection<Item> items;
    private final Payment payment;
    private final String note;

    public UpdateOrderVO(long orderId, Collection<Item> items, Payment payment, String note) {
        this.orderId = orderId;
        this.items = items;
        this.payment = payment;
        this.note = note;
    }
}
