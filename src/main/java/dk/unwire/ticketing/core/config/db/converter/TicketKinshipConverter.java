package dk.unwire.ticketing.core.config.db.converter;

import dk.unwire.ticketing.core.domain.ticket.model.vo.TicketKinship;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter(autoApply = true)
public class TicketKinshipConverter implements AttributeConverter<TicketKinship, Integer> {

    @Override
    public Integer convertToDatabaseColumn(TicketKinship entityValue) {
        return entityValue.getType();
    }

    @Override
    public TicketKinship convertToEntityAttribute(Integer databaseValue) {
        TicketKinship ticketKinship = null;
        if (databaseValue != null) {
            ticketKinship = TicketKinship.fromValue(databaseValue);
        }
        return ticketKinship;
    }
}
