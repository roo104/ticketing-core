package dk.unwire.ticketing.core.domain.application.model;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "application_property")
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class ApplicationProperty {
    @Id
    @Column(name = "id")
    private int id;
    @Column(name = "name")
    @Getter
    private String name;
    @Column(name = "value")
    @Getter
    private String value;

    protected ApplicationProperty(String name, String value) {
        this.name = name;
        this.value = value;
    }

}
