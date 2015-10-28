package dk.unwire.ticketing.core.domain.product.model;

import dk.unwire.ticketing.core.common.model.PropertyMap;
import lombok.Getter;

import javax.persistence.*;
import java.util.Collection;
import java.util.HashSet;

@Entity
@Table(name = "product")
@AssociationOverride(name = "properties", joinColumns = @JoinColumn(name = "product_id", referencedColumnName = "id"))
public class Product extends PropertyMap<ProductProperty> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    @Getter
    private long id;
    @Getter
    @Column(name = "name")
    private String name;
    @Getter
    @Column(name = "type")
    private String type;
    @Getter
    @Column(name = "price")
    private Integer price;
    @Getter
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_validity_id", referencedColumnName = "id")
    private ProductValidity productValidity;
    @Getter
    @Column(name = "application_id")
    private Integer applicationId;
    @Getter
    @Column(name = "deleted")
    private boolean deleted;
    @Getter
    @Column(name = "certificate_enabled")
    private boolean certificateEnabled;
    @Getter
    @Column(name = "activated")
    private boolean activated;
    @Getter
    @Column(name = "product_variant")
    private Integer productVariant;
    @Getter
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "price_table_id")
    private PriceTable priceTable;
    @Getter
    @Column(name = "vat")
    private String vat;
    @Getter
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "product_id", referencedColumnName = "id")
    private Collection<InvalidBuyTime> invalidBuyTimes;


    public Product() {
        this.invalidBuyTimes = new HashSet<>();
    }
    
}
