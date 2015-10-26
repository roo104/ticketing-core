package dk.unwire.ticketing.core.domain.product.model;

import javax.persistence.*;

@Entity
@Table(name = "product_property")
public class ProductProperty {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Basic
    @Column(name = "name")
    private final String name;
    @Basic
    @Column(name = "value")
    private final String value;

    public long getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    public String getValue() {
        return this.value;
    }

    protected ProductProperty() {
        this.name = null;
        this.value = null;
    }

    public ProductProperty(String name, String value) {
        this.name = name;
        this.value = value;
    }
}
