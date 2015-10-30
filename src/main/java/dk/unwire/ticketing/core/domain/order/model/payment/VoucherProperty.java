package dk.unwire.ticketing.core.domain.order.model.payment;


import lombok.Getter;

import javax.persistence.*;

@Entity
@Table(name = "order_voucher_property")
public class VoucherProperty {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected long id;
    @Getter
    @Column(name = "integrator_id")
    private int integratorId;
    @Getter
    @Column(name = "entrypted_payment_password")
    private String encryptedPaymentPassword;
}
