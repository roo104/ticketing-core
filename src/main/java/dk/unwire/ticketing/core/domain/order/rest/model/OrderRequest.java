package dk.unwire.ticketing.core.domain.order.rest.model;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class OrderRequest {

    private List<ItemRequest> items;
    private PaymentsRequest paymentsRequest;
    private String note;
}
