package dk.unwire.ticketing.core.domain.product.model.enums;

import java.time.ZonedDateTime;

public enum TimeDefinition {

    ALL_DAY(-1),
    MONDAY(1),
    TUESDAY(2),
    WEDNESDAY(3),
    THURSDAY(4),
    FRIDAY(5),
    SATURDAY(6),
    SUNDAY(7),
    WEEKDAY(8),
    WEEKEND(9);

    private int definition;

    TimeDefinition(int definition) {
        this.definition = definition;
    }

    public int getDefinition() {
        return this.definition;
    }

    public boolean isWeekday() {
        return this.definition >= MONDAY.definition && this.definition <= FRIDAY.definition;
    }

    public boolean isWeekend() {
        return this.definition >= SATURDAY.definition && this.definition <= SUNDAY.definition;
    }

    public static TimeDefinition fromDefinition(int definitionValue) {
        TimeDefinition timeDefinition = null;

        // Loop through all week days from Monday to Sunday
        for (TimeDefinition definition : TimeDefinition.values()) {
            if (definition.definition == definitionValue) {
                timeDefinition = definition;
                break;
            }
        }
        return timeDefinition;
    }

    /**
     * Returned TimeDefinition will always be in the range Monday to Sunday.
     * @param dateTime
     * @return Returns the week of day represented as a TimeDefinition
     */
    public static TimeDefinition fromDataTime(ZonedDateTime dateTime) {
        int dayOfWeek = dateTime.getDayOfWeek().getValue();
        TimeDefinition timeDefinition = fromDefinition(dayOfWeek);
        return timeDefinition;
    }
}
