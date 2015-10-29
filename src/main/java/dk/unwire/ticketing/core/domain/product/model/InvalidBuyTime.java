package dk.unwire.ticketing.core.domain.product.model;

import lombok.Getter;

import javax.persistence.*;
import java.sql.Time;
import java.time.ZonedDateTime;

@Entity
@Table(name = "invalid_buy_time")
public class InvalidBuyTime {

    @Getter
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
    private Short weekDay;
    @Getter
    @Column(name = "start_time")
    private Time startTime;
    @Getter
    @Column(name = "end_time")
    private Time endTime;
    @Getter
    @Column(name = "inversed")
    private boolean inversed;
    @Getter
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "time_period_id")
    private TimePeriod timePeriod;

    public InvalidBuyTime() {}

}
