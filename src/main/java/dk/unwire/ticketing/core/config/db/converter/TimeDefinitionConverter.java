package dk.unwire.ticketing.core.config.db.converter;

import dk.unwire.ticketing.core.domain.product.model.enums.TimeDefinition;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter(autoApply = true)
public class TimeDefinitionConverter implements AttributeConverter<TimeDefinition, Integer> {

    @Override
    public Integer convertToDatabaseColumn(TimeDefinition entityValue) {
        return entityValue.getDefinition();
    }

    @Override
    public TimeDefinition convertToEntityAttribute(Integer databaseValue) {
        TimeDefinition timeDefinition = null;
        if (databaseValue != null) {
            timeDefinition = TimeDefinition.fromDefinition(databaseValue);
        }
        return timeDefinition;
    }
}

