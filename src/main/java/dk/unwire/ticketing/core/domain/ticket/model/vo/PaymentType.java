package dk.unwire.ticketing.core.domain.ticket.model.vo;

public enum PaymentType {

    PREMIUM_SMS(1),
    NO_PAYMENT(16);

    private int paymentType;

    PaymentType(int paymentType) {
        this.paymentType = paymentType;
    }

    public int getPaymentType() {
        return this.paymentType;
    }

    public static PaymentType fromValue(int paymentTypeValue) {
        PaymentType paymentType = null;

        for (PaymentType payment : PaymentType.values()) {
            if (payment.paymentType == paymentTypeValue) {
                paymentType = payment;
                break;
            }
        }
        return paymentType;
    }
}
