package dk.unwire.ticketing.core.domain.ticket.model;

import lombok.Getter;

import javax.persistence.*;

@Entity
@Table(name = "ticket_property")
public class TicketProperty {

    @Id
    @Getter
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Getter
    @Column(name = "name")
    private String name;
    @Getter
    @Column(name = "value")
    private String value;

}
