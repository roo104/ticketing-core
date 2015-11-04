package dk.unwire.ticketing.core.domain.order.model;

import dk.unwire.ticketing.core.common.model.Property;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "order_property")
public class OrderProperty extends Property {
}
