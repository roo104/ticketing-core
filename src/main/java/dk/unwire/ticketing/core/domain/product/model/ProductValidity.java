package dk.unwire.ticketing.core.domain.product.model;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "product_validity")
public class ProductValidity {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Basic
    @Column(name = "start_time")
    private Timestamp startTime;
    @Basic
    @Column(name = "expire_time")
    private Timestamp expireTime;
    @Basic
    @Column(name = "start_delay_minutes")
    private int startDelayMinutes;
    @Basic
    @Column(name = "end_delay_minutes")
    private Integer endDelayMinutes;
    @Basic
    @Column(name = "start_pattern")
    private String startPattern;
    @Basic
    @Column(name = "expire_pattern")
    private String expirePattern;

    public long getId() {
        return this.id;
    }

    public Timestamp getStartTime() {
        return this.startTime;
    }

    public Timestamp getExpireTime() {
        return this.expireTime;
    }

    public int getStartDelayMinutes() {
        return this.startDelayMinutes;
    }

    public Integer getEndDelayMinutes() {
        return this.endDelayMinutes;
    }

    public String getStartPattern() {
        return this.startPattern;
    }

    public String getExpirePattern() {
        return this.expirePattern;
    }

}
