package dk.unwire.ticketing.core.domain.systemproperty.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="system_property")
public class SystemProperty {
    @Id
    @Column(name = "id")
    private int id;
    @Column(name = "name")
    private String name;
    @Column(name = "value")
    private String value;

    public SystemProperty(int id, String name, String value) {
        this.id = id;
        this.name = name;
        this.value = value;
    }

    public SystemProperty() {
    }

    public String getValue() {
        return this.value;
    }

    public int getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }
}