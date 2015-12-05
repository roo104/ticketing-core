package dk.unwire.ticketing.core.config.db.converter;

import dk.unwire.ticketing.core.domain.ticket.model.vo.PaymentType;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter(autoApply = true)
public class PaymentTypeConverter implements AttributeConverter<PaymentType, Integer> {

    @Override
    public Integer convertToDatabaseColumn(PaymentType entityValue) {
        return entityValue.getPaymentType();
    }

    @Override
    public PaymentType convertToEntityAttribute(Integer databaseValue) {
        PaymentType paymentType = null;
        if (databaseValue != null) {
            paymentType = PaymentType.fromValue(databaseValue);
        }
        return paymentType;
    }
}
