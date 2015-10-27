package dk.unwire.ticketing.core.common.model;

import javax.persistence.*;

/**
 * The class can be extended when you need a simple name/value property class.
 */
@MappedSuperclass
public abstract class Property {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected long id;
    @Basic
    @Column(name = "name")
    protected String name;
    @Basic
    @Column(name = "value")
    protected String value;

    public long getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    public String getValue() {
        return this.value;
    }

    protected Property() {
        this.name = null;
        this.value = null;
    }

    protected Property(String name, String value) {
        this.name = name;
        this.value = value;
    }
}
