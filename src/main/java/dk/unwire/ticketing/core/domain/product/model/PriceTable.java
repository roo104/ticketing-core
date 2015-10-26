package dk.unwire.ticketing.core.domain.product.model;

import javax.persistence.*;

@Entity
@Table(name = "price_table")
public class PriceTable {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;
    private boolean deleted;
    private Integer standardPeriodLength;

    public long getId() {
        return this.id;
    }

    @Basic
    @Column(name = "name")
    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "deleted")
    public boolean getDeleted() {
        return this.deleted;
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }

    @Basic
    @Column(name = "standard_period_length")
    public Integer getStandardPeriodLength() {
        return this.standardPeriodLength;
    }

    public void setStandardPeriodLength(Integer standardPeriodLength) {
        this.standardPeriodLength = standardPeriodLength;
    }

}
