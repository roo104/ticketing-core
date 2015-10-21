package dk.unwire.ticketing.core.domain.product.model;

import javax.persistence.*;

@Entity
@Table(name = "product_property", schema = "", catalog = "mticket_application")
public class ProductProperty {
    private int id;
    private String name;
    private String value;

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
    @Column(name = "value")
    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }


}
