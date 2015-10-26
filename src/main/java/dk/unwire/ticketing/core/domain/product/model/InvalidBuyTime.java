package dk.unwire.ticketing.core.domain.product.model;

import javax.persistence.*;
import java.sql.Time;
import java.sql.Timestamp;

@Entity
@Table(name = "invalid_buy_time")
public class InvalidBuyTime {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private Timestamp startDate;
    private Timestamp endDate;
    private Short weekDay;
    private Time startTime;
    private Time endTime;
    private boolean inversed;
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id")
    private TimePeriod timePeriod;


    public long getId() {
        return this.id;
    }

    @Basic
    @Column(name = "start_date")
    public Timestamp getStartDate() {
        return this.startDate;
    }

    public void setStartDate(Timestamp startDate) {
        this.startDate = startDate;
    }

    @Basic
    @Column(name = "end_date")
    public Timestamp getEndDate() {
        return this.endDate;
    }

    public void setEndDate(Timestamp endDate) {
        this.endDate = endDate;
    }

    @Basic
    @Column(name = "week_day")
    public Short getWeekDay() {
        return this.weekDay;
    }

    public void setWeekDay(Short weekDay) {
        this.weekDay = weekDay;
    }

    @Basic
    @Column(name = "start_time")
    public Time getStartTime() {
        return this.startTime;
    }

    public void setStartTime(Time startTime) {
        this.startTime = startTime;
    }

    @Basic
    @Column(name = "end_time")
    public Time getEndTime() {
        return this.endTime;
    }

    public void setEndTime(Time endTime) {
        this.endTime = endTime;
    }

    @Basic
    @Column(name = "inversed")
    public boolean getInversed() {
        return this.inversed;
    }

    public void setInversed(boolean inversed) {
        this.inversed = inversed;
    }

}
