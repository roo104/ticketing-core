package dk.unwire.ticketing.core.domain.product.model;

import javax.persistence.*;

@Entity
@Table(name = "price_table")
public class PriceTable {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Basic
    @Column(name = "name")
    private String name;
    @Basic
    @Column(name = "deleted")
    private boolean deleted;
    @Basic
    @Column(name = "standard_period_length")
    private Integer standardPeriodLength;

    public long getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    public boolean getDeleted() {
        return this.deleted;
    }

    public Integer getStandardPeriodLength() {
        return this.standardPeriodLength;
    }


}
