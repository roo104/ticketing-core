package dk.unwire.ticketing.core.domain.systemproperty.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="system_property")
public final class SystemProperty {
    @Id
    @Column(name = "id")
    private int id;
    @Column(name = "name")
    private String name;
    @Column(name = "value")
    private String value;

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