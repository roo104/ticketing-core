package dk.unwire.ticketing.core.domain.systemproperty.model;

import lombok.Getter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "system_property")
public final class SystemProperty {
    @Id
    @Column(name = "id")
    @Getter
    private int id;
    @Column(name = "name")
    @Getter
    private String name;
    @Column(name = "value")
    @Getter
    private String value;

    private SystemProperty() {
    }

    protected SystemProperty(String name, String value) {
        this.name = name;
        this.value = value;
    }

}