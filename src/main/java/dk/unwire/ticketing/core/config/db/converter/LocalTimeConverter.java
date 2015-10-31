package dk.unwire.ticketing.core.config.db.converter;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.sql.Time;
import java.time.LocalTime;

@Converter(autoApply = true)
public class LocalTimeConverter implements AttributeConverter<LocalTime, Time> {

    @Override
    public Time convertToDatabaseColumn(LocalTime entityValue) {
        Time time = null;
        if (entityValue != null) {
            time = Time.valueOf(entityValue);
        }
        return time;
    }

    @Override
    public LocalTime convertToEntityAttribute(Time databaseValue) {
        LocalTime localTime = null;
        if (databaseValue != null) {
            localTime = databaseValue.toLocalTime();
        }
        return localTime;
    }

}

