package dk.unwire.ticketing.core.domain.product.model;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "product_validity")
public class ProductValidity {

    private int id;
    private Timestamp startTime;
    private Timestamp expireTime;
    private int startDelayMinutes;
    private Integer endDelayMinutes;
    private String startPattern;
    private String expirePattern;

    @Id
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "start_time")
    public Timestamp getStartTime() {
        return startTime;
    }

    public void setStartTime(Timestamp startTime) {
        this.startTime = startTime;
    }

    @Basic
    @Column(name = "expire_time")
    public Timestamp getExpireTime() {
        return expireTime;
    }

    public void setExpireTime(Timestamp expireTime) {
        this.expireTime = expireTime;
    }

    @Basic
    @Column(name = "start_delay_minutes")
    public int getStartDelayMinutes() {
        return startDelayMinutes;
    }

    public void setStartDelayMinutes(int startDelayMinutes) {
        this.startDelayMinutes = startDelayMinutes;
    }

    @Basic
    @Column(name = "end_delay_minutes")
    public Integer getEndDelayMinutes() {
        return endDelayMinutes;
    }

    public void setEndDelayMinutes(Integer endDelayMinutes) {
        this.endDelayMinutes = endDelayMinutes;
    }

    @Basic
    @Column(name = "start_pattern")
    public String getStartPattern() {
        return startPattern;
    }

    public void setStartPattern(String startPattern) {
        this.startPattern = startPattern;
    }

    @Basic
    @Column(name = "expire_pattern")
    public String getExpirePattern() {
        return expirePattern;
    }

    public void setExpirePattern(String expirePattern) {
        this.expirePattern = expirePattern;
    }

}
