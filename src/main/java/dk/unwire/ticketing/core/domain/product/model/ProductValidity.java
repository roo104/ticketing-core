package dk.unwire.ticketing.core.domain.product.model;

import lombok.Getter;

import javax.persistence.*;
import java.time.ZonedDateTime;

@Entity
@Table(name = "product_validity")
public class ProductValidity {

    @Id
    @Getter
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Getter
    @Column(name = "start_time")
    private ZonedDateTime startTime;
    @Getter
    @Column(name = "expire_time")
    private ZonedDateTime expireTime;
    @Getter
    @Column(name = "start_delay_minutes")
    private int startDelayMinutes;
    @Getter
    @Column(name = "end_delay_minutes")
    private int endDelayMinutes;
    @Getter
    @Column(name = "start_pattern")
    private String startPattern;
    @Getter
    @Column(name = "expire_pattern")
    private String expirePattern;


}
