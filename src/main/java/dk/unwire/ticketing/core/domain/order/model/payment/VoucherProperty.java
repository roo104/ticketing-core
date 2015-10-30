package dk.unwire.ticketing.core.domain.order.model.payment;


import javax.persistence.*;

@Entity
@Table(name = "order_voucher_property")
public class VoucherProperty {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected long id;
    private int integratorId;
    private String voucherUnit;
    private int voucherValue;
    private String encryptedPaymentPassword;
}
