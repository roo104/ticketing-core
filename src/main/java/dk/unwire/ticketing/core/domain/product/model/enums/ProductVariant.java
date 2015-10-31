package dk.unwire.ticketing.core.domain.product.model.enums;

public enum ProductVariant {

    None(0),
    Voucher(1),
    VoucherToken(2),
    Campaign(4),
    Repurchase(8),
    InCatalog(16),
    HasMultiplicity(32),
    NonPriced(64);

    ProductVariant(int value){
        this.value = value;
    }

    private int value;

    public int getValue() {
        return this.value;
    }
}
