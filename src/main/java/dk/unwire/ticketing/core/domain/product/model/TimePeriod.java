package dk.unwire.ticketing.core.domain.product.model;

import javax.persistence.*;
import java.sql.Time;
import java.sql.Timestamp;

@Entity
@Table(name = "time_period")
public class TimePeriod {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Basic
    @Column(name = "start_date")
    private Timestamp startDate;
    @Basic
    @Column(name = "end_date")
    private Timestamp endDate;
    @Basic
    @Column(name = "week_day")
    private Short weekDay;
    @Basic
    @Column(name = "start_time")
    private Time startTime;
    @Basic
    @Column(name = "end_time")
    private Time endTime;
    @Basic
    @Column(name = "holiday_compliance")
    private String holidayCompliance;
    @Basic
    @Column(name = "time_zone")
    private String timeZone;

    public long getId() {
        return this.id;
    }

    public Timestamp getStartDate() {
        return this.startDate;
    }

    public Timestamp getEndDate() {
        return this.endDate;
    }

    public Short getWeekDay() {
        return this.weekDay;
    }

    public Time getStartTime() {
        return this.startTime;
    }

    public Time getEndTime() {
        return this.endTime;
    }

    public String getHolidayCompliance() {
        return this.holidayCompliance;
    }

    public String getTimeZone() {
        return this.timeZone;
    }

}
