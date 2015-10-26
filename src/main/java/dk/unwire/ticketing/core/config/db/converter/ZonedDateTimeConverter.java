package dk.unwire.ticketing.core.config.db.converter;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.sql.Timestamp;
import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;

@Converter(autoApply = true)
public class ZonedDateTimeConverter implements AttributeConverter<ZonedDateTime, Timestamp> {

    private static final long SECOND_IN_MILLISECONDS = 1000L;

    @Override
    public Timestamp convertToDatabaseColumn(ZonedDateTime entityValue) {
        Timestamp timestamp = null;
        if (entityValue != null) {
            timestamp = Timestamp.from(entityValue.toInstant());
        }
        return timestamp;
    }

    @Override
    public ZonedDateTime convertToEntityAttribute(Timestamp databaseValue) {
        ZonedDateTime zonedDateTime = null;
        if (databaseValue != null) {
            zonedDateTime = ZonedDateTime.ofInstant(Instant.ofEpochSecond(databaseValue.getTime() / SECOND_IN_MILLISECONDS), ZoneId.of("UTC"));
        }
        return zonedDateTime;
    }

}

