package dk.unwire.ticketing.core.domain.product.model;

import lombok.Getter;

import javax.persistence.*;

@Entity
@Table(name = "price")
public class Price {

    @Id
    @Getter
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Getter
    @Column(name = "value")
    private Integer value;
    @Getter
    @Column(name = "value_in_tokens")
    private Integer valueInTokens;
    @Getter
    @Column(name = "pricable_type")
    private Integer pricableType;
    @Getter
    @Column(name = "token_pool_id")
    private Integer tokenPoolId;
    @Getter
    @Column(name = "price_category_type")
    private Integer priceCategoryType;


}
