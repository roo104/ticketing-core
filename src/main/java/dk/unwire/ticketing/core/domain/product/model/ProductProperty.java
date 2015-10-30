package dk.unwire.ticketing.core.domain.product.model;

import dk.unwire.ticketing.core.common.model.Property;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "product_property")
public class ProductProperty extends Property {

}
