package dk.unwire.ticketing.core.domain.order.rest.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import dk.unwire.ticketing.core.domain.order.model.payment.Payment;

@JsonIgnoreProperties(ignoreUnknown = true)
public class PaymentsRequest {


    public Payment toPayment() {
        return null;
    }
}
