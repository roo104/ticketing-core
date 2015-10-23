package dk.unwire.ticketing.core.domain.product.model;

import javax.persistence.*;

@Entity
@Table(name = "price_table")
public class PriceTable {

    private int id;
    private String name;
    private boolean deleted;
    private Integer standardPeriodLength;

    @Id
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "deleted")
    public boolean getDeleted() {
        return deleted;
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }

    @Basic
    @Column(name = "standard_period_length")
    public Integer getStandardPeriodLength() {
        return standardPeriodLength;
    }

    public void setStandardPeriodLength(Integer standardPeriodLength) {
        this.standardPeriodLength = standardPeriodLength;
    }

}
