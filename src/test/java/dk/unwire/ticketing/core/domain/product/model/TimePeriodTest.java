package dk.unwire.ticketing.core.domain.product.model;

import dk.unwire.ticketing.core.domain.product.model.enums.TimeDefinition;
import org.junit.Test;

import java.time.LocalTime;
import java.time.ZonedDateTime;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class TimePeriodTest {

    private ZonedDateTime startDateTime = ZonedDateTime.parse("2015-10-01T00:00:00+00:00[UTC]");
    private ZonedDateTime endDateTime = ZonedDateTime.parse("2015-10-02T00:00:00+00:00[UTC]");

    private LocalTime startLocalTime = LocalTime.parse("10:00:00");
    private LocalTime endLocalTime = LocalTime.parse("12:00:00");

    @Test
    public void validPeriodwithStartAndEndDateIsValid() {
        // given
        TimePeriod timePeriod = getTimePeriod(true, false, TimeDefinition.ALL_DAY);

        // when
        ZonedDateTime dateTime = ZonedDateTime.parse("2015-10-01T11:00:00+00:00[UTC]");

        // then
        assertTrue(timePeriod.isTimeValid(dateTime));
    }

    @Test
    public void validPeriodwithStartAndEndDateIsNotValid() {
        // given
        TimePeriod timePeriod = getTimePeriod(true, false, TimeDefinition.ALL_DAY);

        // when
        ZonedDateTime dateTime = ZonedDateTime.parse("2015-10-02T11:00:00+00:00[UTC]");

        // then
        assertFalse(timePeriod.isTimeValid(dateTime));
    }

    @Test
    public void validPeriodwithStartAndEndDateWrongWeekday_Weekend() {
        // given
        TimePeriod timePeriod = getTimePeriod(true, false, TimeDefinition.WEEKEND);

        // when
        ZonedDateTime dateTime = ZonedDateTime.parse("2015-10-01T11:00:00+00:00[UTC]");

        // then
        assertFalse(timePeriod.isTimeValid(dateTime));
    }

    @Test
    public void validPeriodwithStartAndEndDateWrongWeekday_Weekday() {
        // given
        TimePeriod timePeriod = getTimePeriod(true, false, TimeDefinition.WEEKDAY);

        // when
        ZonedDateTime dateTime = ZonedDateTime.parse("2015-10-01T11:00:00+00:00[UTC]");

        // then
        assertTrue(timePeriod.isTimeValid(dateTime));
    }

    @Test
    public void validPeriodwithStartAndEndDateWrongWeekday_Monday() {
        // given
        TimePeriod timePeriod = getTimePeriod(true, false, TimeDefinition.MONDAY);

        // when
        ZonedDateTime dateTime = ZonedDateTime.parse("2015-10-01T11:00:00+00:00[UTC]");

        // then
        assertFalse(timePeriod.isTimeValid(dateTime));
    }

    @Test
    public void validPeriodwithStartAndEndDateWrongWeekday_ExactDay() {
        // given
        TimePeriod timePeriod = getTimePeriod(true, false, TimeDefinition.THURSDAY);

        // when
        ZonedDateTime dateTime = ZonedDateTime.parse("2015-10-01T11:00:00+00:00[UTC]");

        // then
        assertTrue(timePeriod.isTimeValid(dateTime));
    }

    @Test
    public void validPeriodwithStartAndEndDateIsValid_InValidTime() {
        // given
        TimePeriod timePeriod = getTimePeriod(true, true, TimeDefinition.ALL_DAY);

        // when
        ZonedDateTime dateTime = ZonedDateTime.parse("2015-10-01T11:00:00+00:00[UTC]");

        // then
        assertFalse(timePeriod.isTimeValid(dateTime));
    }

    @Test
    public void validPeriodwithStartAndEndDateIsValid_ValidTime() {
        // given
        TimePeriod timePeriod = getTimePeriod(true, true, TimeDefinition.ALL_DAY);

        // when
        ZonedDateTime dateTime = ZonedDateTime.parse("2015-10-01T09:00:00+00:00[UTC]");

        // then
        assertTrue(timePeriod.isTimeValid(dateTime));
    }

    private TimePeriod getTimePeriod(boolean withDate, boolean withTime, TimeDefinition timeDefinition) {
        ZonedDateTime startDate = (withDate) ? this.startDateTime : null;
        ZonedDateTime endDate = (withDate) ? this.endDateTime : null;

        LocalTime startTime = (withTime) ? this.startLocalTime : null;
        LocalTime endTime = (withTime) ? this.endLocalTime : null;

        return TimePeriod.newBuilder()
                .withDates(startDate, endDate)
                .withWeekDay(timeDefinition)
                .withTimes(startTime, endTime)
                .withTimeZone("Europe/Copenhagen")
                .build();
    }
}