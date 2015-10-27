package dk.unwire.ticketing.core.domain.application.model;

import dk.unwire.ticketing.core.common.model.Property;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "application_property")
public class ApplicationProperty extends Property {

    protected ApplicationProperty() {
    }

    public ApplicationProperty(String name, String value) {
        super(name, value);
    }
}
