package dk.unwire.ticketing.core.domain.product.model;

import javax.persistence.*;
import java.util.Collection;

@Entity
public class Product {

    @Id
    @Column(name = "id")
    private int id;
    private String name;
    private String type;
    private Integer price;
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_validity_id")
    private ProductValidity productValidity;
    private Integer applicationId;
    private boolean deleted;
    private boolean certificateEnabled;
    private boolean activated;
    private Integer productVariant;
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "price_table_id")
    private PriceTable priceTable;
    private String vat;
    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id", referencedColumnName = "id")
    private Collection<ProductProperty> productProperties;
    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id", referencedColumnName = "id")
    private Collection<InvalidBuyTime> invalidBuyTimes;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "type")
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Basic
    @Column(name = "price")
    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    @Basic
    @Column(name = "application_id")
    public Integer getApplicationId() {
        return applicationId;
    }

    public void setApplicationId(Integer applicationId) {
        this.applicationId = applicationId;
    }

    @Basic
    @Column(name = "deleted")
    public boolean getDeleted() {
        return deleted;
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }

    @Basic
    @Column(name = "certificate_enabled")
    public boolean getCertificateEnabled() {
        return certificateEnabled;
    }

    public void setCertificateEnabled(boolean certificateEnabled) {
        this.certificateEnabled = certificateEnabled;
    }

    @Basic
    @Column(name = "activated")
    public boolean getActivated() {
        return activated;
    }

    public void setActivated(boolean activated) {
        this.activated = activated;
    }

    @Basic
    @Column(name = "product_variant")
    public Integer getProductVariant() {
        return productVariant;
    }

    public void setProductVariant(Integer productVariant) {
        this.productVariant = productVariant;
    }

    @Basic
    @Column(name = "vat")
    public String getVat() {
        return vat;
    }

    public void setVat(String vat) {
        this.vat = vat;
    }

    public boolean isAllowedToPurchase() {
        return false;
    }
}
