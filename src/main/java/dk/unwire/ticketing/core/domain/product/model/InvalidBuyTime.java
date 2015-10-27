package dk.unwire.ticketing.core.domain.product.model;

import lombok.Builder;

import javax.persistence.*;
import java.sql.Time;
import java.sql.Timestamp;

@Entity
@Table(name = "invalid_buy_time")
@Builder
public class InvalidBuyTime {

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
    @Column(name = "inversed")
    private boolean inversed;
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id")
    private TimePeriod timePeriod;

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

    public boolean getInversed() {
        return this.inversed;
    }

}
