package dk.unwire.ticketing.core.domain.order.rest.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import dk.unwire.ticketing.core.domain.order.model.Item;
import dk.unwire.ticketing.core.domain.order.model.payment.Payment;
import dk.unwire.ticketing.core.domain.order.model.vo.UpdateOrderVO;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class UpdateOrderRequest {

    private List<ItemRequest> items;
    private PaymentsRequest payments;
    private String note;

    public UpdateOrderVO toUpdateOrderVO(long orderId) {
        Payment payment = null;
        if (this.payments != null) {
            payment = this.payments.toPayment();
        }
        return new UpdateOrderVO(orderId, toItemList(), payment, this.note);
    }

    private List<Item> toItemList() {
        return this.items.stream().map(ItemRequest::toItem).collect(Collectors.toList());
    }

    @Override
    public String toString() {
        return "UpdateOrderRequest{" +
                "items=" + this.items +
                ", payments=" + this.payments +
                ", note='" + this.note + '\'' +
                '}';
    }
}
