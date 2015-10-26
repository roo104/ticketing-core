package dk.unwire.ticketing.core.domain.product.model;

import javax.persistence.*;
import java.time.ZonedDateTime;
import java.util.Collection;
import java.util.HashSet;

@Entity
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;
    @Basic
    @Column(name = "name")
    private String name;
    @Basic
    @Column(name = "type")
    private String type;
    @Basic
    @Column(name = "price")
    private Integer price;
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_validity_id")
    private ProductValidity productValidity;
    @Basic
    @Column(name = "application_id")
    private Integer applicationId;
    @Basic
    @Column(name = "deleted")
    private boolean deleted;
    @Basic
    @Column(name = "certificate_enabled")
    private boolean certificateEnabled;
    @Basic
    @Column(name = "activated")
    private boolean activated;
    @Basic
    @Column(name = "product_variant")
    private Integer productVariant;
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "price_table_id")
    private PriceTable priceTable;
    @Basic
    @Column(name = "vat")
    private String vat;
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "product_id", referencedColumnName = "id")
    private Collection<ProductProperty> productProperties;
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "product_id", referencedColumnName = "id")
    private Collection<InvalidBuyTime> invalidBuyTimes;

    public Product() {
        this.productProperties = new HashSet<>();
        this.invalidBuyTimes = new HashSet<>();
    }

    public long getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    public String getType() {
        return this.type;
    }

    public Integer getPrice() {
        return this.price;
    }

    public Integer getApplicationId() {
        return this.applicationId;
    }

    public boolean getDeleted() {
        return this.deleted;
    }

    public boolean getCertificateEnabled() {
        return this.certificateEnabled;
    }

    public boolean getActivated() {
        return this.activated;
    }

    public Integer getProductVariant() {
        return this.productVariant;
    }

    public String getVat() {
        return this.vat;
    }

    public boolean isAllowedToPurchase() {
        return false;
    }

    public Collection<InvalidBuyTime> getInvalidBuyTimes() {
        return this.invalidBuyTimes;
    }

    public Collection<ProductProperty> getProductProperties() {
        return this.productProperties;
    }

    public void addProductProperty(String name, String value) {
        this.productProperties.add(new ProductProperty(name, value));
    }

    public boolean canBeBought(ZonedDateTime buyTime) {

        return true;

    }

    public boolean canBeActivated(ZonedDateTime buyTime) {
        return true;
    }
}
