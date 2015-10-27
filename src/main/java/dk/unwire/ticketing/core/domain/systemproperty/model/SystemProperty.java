package dk.unwire.ticketing.core.domain.systemproperty.model;

import dk.unwire.ticketing.core.common.model.Property;

import javax.persistence.Entity;
import javax.persistence.Table;

@Table(name = "system_property")
@Entity
public final class SystemProperty extends Property {

    public SystemProperty() {
    }

    public SystemProperty(String name, String value) {
        super(name, value);
    }
}