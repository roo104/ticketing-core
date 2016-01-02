package dk.unwire.ticketing.core.domain.order.rest.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import dk.unwire.ticketing.core.domain.order.model.Item;
import lombok.Getter;

@JsonIgnoreProperties(ignoreUnknown = true)
@Getter
public class ItemRequest {

    private final int productCount;
    private final long productId;

    @JsonCreator
    public ItemRequest(@JsonProperty("productCount") int productCount, @JsonProperty("productId") long productId) {
        this.productCount = productCount;
        this.productId = productId;
    }

    public Item toItem() {
        return new Item(this.productId, this.productCount);
    }

    @Override
    public String toString() {
        return "ItemRequest{" +
                "productCount=" + this.productCount +
                ", productId=" + this.productId +
                '}';
    }
}
