package dk.unwire.ticketing.core.domain.order.model.payment;

import javax.persistence.CascadeType;
import javax.persistence.Embeddable;
import javax.persistence.OneToMany;
import java.util.Collection;

/**
 * Embedded class holding property references. Business logic dealing with properties can be made here.
 */
@Embeddable
public class Payment {

    @OneToMany(cascade = CascadeType.ALL)
    private Collection<IntegratorProperty> integratorProperties;
    @OneToMany(cascade = CascadeType.ALL)
    private Collection<CardProperty> cardProperties;
    @OneToMany(cascade = CascadeType.ALL)
    private Collection<VoucherProperty> voucherProperties;

}
