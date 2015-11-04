package dk.unwire.ticketing.core.domain.application.model;

import lombok.Getter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Country {

    @Id
    @Getter
    @Column(name = "id")
    private int id;
    @Getter
    @Column(name = "currency")
    private String currency;
    @Getter
    @Column(name = "code")
    private String code;
    @Getter
    @Column(name = "name")
    private String name;
    @Getter
    @Column(name = "msisdn_length")
    private Integer msisdnLength;
    @Getter
    @Column(name = "msisdn_prefix")
    private Integer msisdnPrefix;

}
