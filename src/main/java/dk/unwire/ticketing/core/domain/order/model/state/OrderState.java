package dk.unwire.ticketing.core.domain.order.model.state;

public enum OrderState {

    CREATED(0),
    CHECKOUT(10),
    VALIDATED(20),
    VALIDATION_FAILED(21),
    PAYMENT_RESERVE_INIT(30),
    PAYMENT_RESERVE_COMPLETED(40),
    PAYMENT_RESERVE_FAILED(41),
    PAYMENT_WITHDRAW_INIT(50),
    PAYMENT_WITHDRAW_COMPLETED(60),
    PAYMENT_WITHDRAW_FAILED(61),
    COMPLETED(100);

    private int state;

    OrderState(int state) {
        this.state = state;
    }

}
