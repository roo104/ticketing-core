package dk.unwire.ticketing.core.domain.order.model.payment;

import javax.persistence.*;

@Entity
@Table(name = "order_integrator_property")
public class IntegratorProperty {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected long id;
    @Column(name = "integrator_id")
    private int integratorId;
    @Column(name = "scheme_id")
    private int schemeId;
    @Column(name = "store_card")
    private boolean storeCard;
}
