package dk.unwire.ticketing.core.domain.product.model;

import javax.persistence.*;
import java.sql.Time;
import java.sql.Timestamp;

@Entity
@Table(name = "time_period", schema = "", catalog = "mticket_application")
public class TimePeriod {
    private int id;
    private Timestamp startDate;
    private Timestamp endDate;
    private Short weekDay;
    private Time startTime;
    private Time endTime;
    private String holidayCompliance;
    private String timeZone;

    @Id
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "start_date")
    public Timestamp getStartDate() {
        return startDate;
    }

    public void setStartDate(Timestamp startDate) {
        this.startDate = startDate;
    }

    @Basic
    @Column(name = "end_date")
    public Timestamp getEndDate() {
        return endDate;
    }

    public void setEndDate(Timestamp endDate) {
        this.endDate = endDate;
    }

    @Basic
    @Column(name = "week_day")
    public Short getWeekDay() {
        return weekDay;
    }

    public void setWeekDay(Short weekDay) {
        this.weekDay = weekDay;
    }

    @Basic
    @Column(name = "start_time")
    public Time getStartTime() {
        return startTime;
    }

    public void setStartTime(Time startTime) {
        this.startTime = startTime;
    }

    @Basic
    @Column(name = "end_time")
    public Time getEndTime() {
        return endTime;
    }

    public void setEndTime(Time endTime) {
        this.endTime = endTime;
    }

    @Basic
    @Column(name = "holiday_compliance")
    public String getHolidayCompliance() {
        return holidayCompliance;
    }

    public void setHolidayCompliance(String holidayCompliance) {
        this.holidayCompliance = holidayCompliance;
    }

    @Basic
    @Column(name = "time_zone")
    public String getTimeZone() {
        return timeZone;
    }

    public void setTimeZone(String timeZone) {
        this.timeZone = timeZone;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TimePeriod that = (TimePeriod) o;

        if (id != that.id) return false;
        if (startDate != null ? !startDate.equals(that.startDate) : that.startDate != null) return false;
        if (endDate != null ? !endDate.equals(that.endDate) : that.endDate != null) return false;
        if (weekDay != null ? !weekDay.equals(that.weekDay) : that.weekDay != null) return false;
        if (startTime != null ? !startTime.equals(that.startTime) : that.startTime != null) return false;
        if (endTime != null ? !endTime.equals(that.endTime) : that.endTime != null) return false;
        if (holidayCompliance != null ? !holidayCompliance.equals(that.holidayCompliance) : that.holidayCompliance != null)
            return false;
        return !(timeZone != null ? !timeZone.equals(that.timeZone) : that.timeZone != null);

    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (startDate != null ? startDate.hashCode() : 0);
        result = 31 * result + (endDate != null ? endDate.hashCode() : 0);
        result = 31 * result + (weekDay != null ? weekDay.hashCode() : 0);
        result = 31 * result + (startTime != null ? startTime.hashCode() : 0);
        result = 31 * result + (endTime != null ? endTime.hashCode() : 0);
        result = 31 * result + (holidayCompliance != null ? holidayCompliance.hashCode() : 0);
        result = 31 * result + (timeZone != null ? timeZone.hashCode() : 0);
        return result;
    }
}
