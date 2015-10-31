package dk.unwire.ticketing.core.domain.product.model;

import dk.unwire.ticketing.core.common.model.PropertyMap;
import dk.unwire.ticketing.core.domain.application.model.Application;
import dk.unwire.ticketing.core.domain.product.model.enums.ProductVariant;
import lombok.Getter;

import javax.persistence.*;
import java.time.ZonedDateTime;
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
    private int price;
    @Getter
    @Column(name = "vat")
    private String vat;
    @Getter
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_validity_id", referencedColumnName = "id")
    private ProductValidity productValidity;
    @Getter
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "application_id", referencedColumnName = "id")
    private Application application;
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
    private int productVariant;
    @Getter
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id", referencedColumnName = "pricable_id")
    private Price voucherPrice;
    @Getter
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(name = "invalid_buy_time", joinColumns = @JoinColumn(name = "id"), inverseJoinColumns = @JoinColumn(name = "time_period_id"))
    private Collection<TimePeriod> buyTimes;

    public Product() {
        this.buyTimes = new HashSet<>();
    }

    private Product(Builder builder) {
        this.deleted = false;
        this.activated = true;
        this.name = builder.name;
        this.type = builder.type;
        this.price = builder.price;
        this.vat = builder.vat;
        this.productValidity = builder.productValidity;
        this.application = builder.application;
        this.certificateEnabled = builder.certificateEnabled;
        this.productVariant = builder.productVariant;
        this.voucherPrice = builder.voucherPrice;
        this.buyTimes = builder.buyTimes;
    }

    /**
     * Determines if the product is a voucher, based on product variant.
     *
     * @return Returns true if the products is of type voucher.
     */
    public boolean isVoucherProduct() {
        int voucherVariant = ProductVariant.Voucher.getValue() + ProductVariant.VoucherToken.getValue();
        return (voucherVariant & this.productVariant) == voucherVariant;
    }

    /**
     *
     * @param dateTime
     * @return
     */
    public boolean isAllowedToBuy(ZonedDateTime dateTime) {
        boolean allowedToBuy = true;
        for (TimePeriod timePeriod : this.buyTimes) {
            allowedToBuy = timePeriod.isTimeValid(dateTime);
            if (!allowedToBuy) {
                break;
            }
        }
        return allowedToBuy;
    }


    public static IName newBuilder() {
        return new Builder();
    }

    public interface IName {
        IType withName(String name);
    }

    public interface IType {
        IApplication withType(String type);
    }

    public interface IApplication {
        IOptional withApplication(Application application);
    }

    public interface IOptional {
        IOptional withPrice(int price, String vat);

        IOptional withProductValidity(ProductValidity productValidity);

        IOptional withCertificateEnabled();

        IOptional withProductVariant(int productVariant);

        IOptional withVoucherPrice(Price voucherPrice);

        IOptional withBuyTimes(Collection<TimePeriod> buyTimes);

        Product build();
    }

    private static final class Builder implements IName, IType, IApplication, IOptional {
        private String name;
        private String type;
        private int price;
        private String vat;
        private ProductValidity productValidity;
        private Application application;
        private boolean certificateEnabled;
        private int productVariant;
        private Price voucherPrice;
        private Collection<TimePeriod> buyTimes;

        @Override
        public IType withName(String name) {
            this.name = name;
            return this;
        }

        @Override
        public IApplication withType(String type) {
            this.type = type;
            return this;
        }

        @Override
        public IOptional withApplication(Application application) {
            this.application = application;
            return this;
        }

        @Override
        public IOptional withPrice(int price, String vat) {
            this.price = price;
            this.vat = vat;
            return this;
        }

        @Override
        public IOptional withProductValidity(ProductValidity productValidity) {
            this.productValidity = productValidity;
            return this;
        }

        @Override
        public IOptional withCertificateEnabled() {
            this.certificateEnabled = true;
            return this;
        }

        @Override
        public IOptional withProductVariant(int productVariant) {
            this.productVariant = productVariant;
            return this;
        }

        @Override
        public IOptional withVoucherPrice(Price voucherPrice) {
            this.voucherPrice = voucherPrice;
            return this;
        }

        @Override
        public IOptional withBuyTimes(Collection<TimePeriod> buyTimes) {
            this.buyTimes = buyTimes;
            return this;
        }

        @Override
        public Product build() {
            return new Product(this);
        }
    }
}
