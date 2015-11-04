package dk.unwire.ticketing.core.domain.ticket.model;

import dk.unwire.ticketing.core.common.model.Property;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "ticket_property")
public class TicketProperty extends Property {

}
