package dk.unwire.ticketing.core.domain.order.rest.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

@Getter
public class ItemRequest {

    private final int productCount;
    private final long productId;

    @JsonCreator
    public ItemRequest(@JsonProperty("productCount") int productCount, @JsonProperty("productId") long productId) {
        this.productCount = productCount;
        this.productId = productId;
    }
}
