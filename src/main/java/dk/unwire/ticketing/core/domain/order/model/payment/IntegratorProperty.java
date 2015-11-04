package dk.unwire.ticketing.core.domain.order.model.payment;

import lombok.Getter;

import javax.persistence.*;

@Entity
@Table(name = "order_integrator_property")
public class IntegratorProperty {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected long id;
    @Getter
    @Column(name = "integrator_id")
    private int integratorId;
    @Getter
    @Column(name = "scheme_id")
    private int schemeId;
    @Getter
    @Column(name = "store_card")
    private boolean storeCard;
}
