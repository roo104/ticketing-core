package dk.unwire.ticketing.core.domain.application.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "application_property")
public final class ApplicationProperty {
    @Id
    @Column(name = "id")
    private int id;
    @Column(name = "name")
    private String name;
    @Column(name = "value")
    private String value;

    private ApplicationProperty() {
    }

    protected ApplicationProperty(String name, String value) {
        this.name = name;
        this.value = value;
    }

    public String getValue() {
        return this.value;
    }

    public String getName() {
        return this.name;
    }
}
