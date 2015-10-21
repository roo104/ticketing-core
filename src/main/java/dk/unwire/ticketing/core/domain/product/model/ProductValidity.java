package dk.unwire.ticketing.core.domain.product.model;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "product_validity", schema = "", catalog = "mticket_application")
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ProductValidity that = (ProductValidity) o;

        if (id != that.id) return false;
        if (startDelayMinutes != that.startDelayMinutes) return false;
        if (startTime != null ? !startTime.equals(that.startTime) : that.startTime != null) return false;
        if (expireTime != null ? !expireTime.equals(that.expireTime) : that.expireTime != null) return false;
        if (endDelayMinutes != null ? !endDelayMinutes.equals(that.endDelayMinutes) : that.endDelayMinutes != null)
            return false;
        if (startPattern != null ? !startPattern.equals(that.startPattern) : that.startPattern != null) return false;
        return !(expirePattern != null ? !expirePattern.equals(that.expirePattern) : that.expirePattern != null);

    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (startTime != null ? startTime.hashCode() : 0);
        result = 31 * result + (expireTime != null ? expireTime.hashCode() : 0);
        result = 31 * result + startDelayMinutes;
        result = 31 * result + (endDelayMinutes != null ? endDelayMinutes.hashCode() : 0);
        result = 31 * result + (startPattern != null ? startPattern.hashCode() : 0);
        result = 31 * result + (expirePattern != null ? expirePattern.hashCode() : 0);
        return result;
    }
}
