package dk.unwire.ticketing.core.domain.order.model.state;

public enum OrderState {

    CREATED(0) {
        @Override
        OrderState nextStateOk() {
            return null;
        }

        @Override
        OrderState nextStateError() {
            return null;
        }
    },
    CHECKOUT(10) {
        @Override
        OrderState nextStateOk() {
            return null;
        }

        @Override
        OrderState nextStateError() {
            return null;
        }
    },
    VALIDATED(20) {
        @Override
        OrderState nextStateOk() {
            return null;
        }

        @Override
        OrderState nextStateError() {
            return null;
        }
    },
    VALIDATION_FAILED(21) {
        @Override
        OrderState nextStateOk() {
            return null;
        }

        @Override
        OrderState nextStateError() {
            return null;
        }
    },
    //    PAYMENT_RESERVE_INIT(30),
//    PAYMENT_RESERVE_COMPLETED(40),
//    PAYMENT_RESERVE_FAILED(41),
//    PAYMENT_WITHDRAW_INIT(50),
//    PAYMENT_WITHDRAW_COMPLETED(60),
//    PAYMENT_WITHDRAW_FAILED(61),
    COMPLETED(100) {
        @Override
        OrderState nextStateOk() {
            return null;
        }

        @Override
        OrderState nextStateError() {
            return null;
        }
    };

    private int state;

    OrderState(int state) {
        this.state = state;
    }

    abstract OrderState nextStateOk();

    abstract OrderState nextStateError();
}
