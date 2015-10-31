package dk.unwire.ticketing.core.domain.product.model;

import dk.unwire.ticketing.core.domain.product.model.enums.TimeDefinition;
import lombok.Getter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.*;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;

@Entity
@Table(name = "time_period")
public class TimePeriod {

    private static final Logger logger = LoggerFactory.getLogger(TimePeriod.class);

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Getter
    @Column(name = "start_date")
    private ZonedDateTime startDate;
    @Getter
    @Column(name = "end_date")
    private ZonedDateTime endDate;
    @Getter
    @Column(name = "week_day")
    private TimeDefinition weekDay;
    @Getter
    @Column(name = "start_time")
    private LocalTime startTime;
    @Getter
    @Column(name = "end_time")
    private LocalTime endTime;
    @Getter
    @Column(name = "time_zone")
    private String timeZone;

    private TimePeriod() {
    }

    private TimePeriod(Builder builder) {
        this.startDate = builder.startDate;
        this.endDate = builder.endDate;
        this.weekDay = builder.weekDay;
        this.startTime = builder.startTime;
        this.endTime = builder.endTime;
        this.timeZone = builder.timeZone;
    }

    public boolean isTimeValid(ZonedDateTime dateTime) {
        boolean withinDateRange = isWithinDateRange(dateTime);
        if (withinDateRange) {
            logger.debug("dateTime[{}} is within date range, now check time range", dateTime);
            withinDateRange = isWithinWeekdayRange(dateTime);
            if (withinDateRange) {
                withinDateRange = isWithinTimeRange(dateTime);
            }
        } else {
            logger.debug("dateTime[{}} is not within date range, return false", dateTime);
        }
        return withinDateRange;
    }

    /**
     * Start date has to be null or before dateTime
     * End date has to be null or after dateTime
     *
     * @param dateTime dateTime where timezone has been applied
     */
    private boolean isWithinDateRange(ZonedDateTime dateTime) {
        boolean isWithinDateRange = true;
        if (this.startDate != null && dateTime.isBefore(this.startDate)) {
            isWithinDateRange = false;
        }
        if (this.endDate != null && dateTime.isAfter(this.endDate)) {
            isWithinDateRange = false;
        }

        return isWithinDateRange;
    }

    private boolean isWithinWeekdayRange(ZonedDateTime dateTime) {
        boolean isWithinTimeRange = false;

        TimeDefinition providedTimeDefinition = TimeDefinition.fromDataTime(dateTime);

        if (TimeDefinition.ALL_DAY == this.weekDay) { // if weekDay is ALL_DAY we don't need to check anything else
            logger.debug("dateTime[{}], timeDefinition[{}], providedTimeDefinition[{}] is ALL_DAY, return true", dateTime, this.weekDay, providedTimeDefinition);
            isWithinTimeRange = true;
        } else if (TimeDefinition.WEEKDAY == this.weekDay && providedTimeDefinition.isWeekday()) { // if weekDay is WEEKDAY and our dateTime is also WEEKDAY
            logger.debug("dateTime[{}], timeDefinition[{}], providedTimeDefinition[{}] is WEEKDAY, return true", dateTime, this.weekDay, providedTimeDefinition);
            isWithinTimeRange = true;
        } else if (TimeDefinition.WEEKEND == this.weekDay && providedTimeDefinition.isWeekend()) { // if weekDay is WEEKEND and our dateTime is also WEEKEND
            logger.debug("dateTime[{}], timeDefinition[{}], providedTimeDefinition[{}] is WEEKEND, return true", dateTime, this.weekDay, providedTimeDefinition);
            isWithinTimeRange = true;
        } else if (this.weekDay == providedTimeDefinition) { // if weekDay is a special day and dateTime is also a special day
            logger.debug("dateTime[{}], timeDefinition[{}], providedTimeDefinition[{}] is equals, return true", dateTime, this.weekDay, providedTimeDefinition);
            isWithinTimeRange = true;
        } else {
            logger.debug("dateTime[{}], timeDefinition[{}], providedTimeDefinition[{}] is not within time range, return false", dateTime, this.weekDay, providedTimeDefinition);

        }
        return isWithinTimeRange;
    }

    private boolean isWithinTimeRange(ZonedDateTime utcDateTime) {
        boolean isWithinTimeRange = true;
        ZoneId zone = ZoneId.of(this.timeZone);

        ZonedDateTime zonedDateTime = utcDateTime.withZoneSameInstant(zone);
        LocalTime localTime = zonedDateTime.toLocalTime();

        if (this.startTime != null && this.endDate != null) {
            logger.debug("startTime[{}], endTime[{}], localTime[{}], localDate[{}]", this.startTime, this.endTime, localTime, zonedDateTime);
            isWithinTimeRange = localTime.isAfter(this.startTime) && localTime.isBefore(this.endTime);
        }

        return isWithinTimeRange;
    }


    public static FirstStep newBuilder() {
        return new Builder();
    }

    public interface FirstStep {
        FirstStep withDates(ZonedDateTime startDate, ZonedDateTime endDate);

        SecondStep withWeekDay(TimeDefinition weekDay);
    }

    public interface SecondStep {
        SecondStep withTimes(LocalTime startTime, LocalTime endTime);

        LastStep withTimeZone(String timeZone);
    }

    public interface LastStep {
        TimePeriod build();
    }

    private static final class Builder implements FirstStep, SecondStep, LastStep {
        private ZonedDateTime startDate;
        private ZonedDateTime endDate;
        private TimeDefinition weekDay;
        private LocalTime startTime;
        private LocalTime endTime;
        private String timeZone;

        private Builder() {
            this.weekDay = TimeDefinition.ALL_DAY;
        }

        @Override
        public FirstStep withDates(ZonedDateTime startDate, ZonedDateTime endDate) {
            this.startDate = startDate;
            this.endDate = endDate;
            return this;
        }

        @Override
        public SecondStep withWeekDay(TimeDefinition weekDay) {
            this.weekDay = weekDay;
            return this;
        }

        @Override
        public SecondStep withTimes(LocalTime startTime, LocalTime endTime) {
            this.startTime = startTime;
            this.endTime = endTime;
            return this;
        }

        @Override
        public LastStep withTimeZone(String timeZone) {
            this.timeZone = timeZone;
            return this;
        }

        @Override
        public TimePeriod build() {
            return new TimePeriod(this);
        }
    }
}
