package dk.unwire.ticketing.core.domain.order.model.payment;

import lombok.Getter;

import javax.persistence.*;

@Entity
@Table(name = "order_card_property")
public class CardProperty {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected long id;
    @Getter
    @Column(name = "card_id")
    private long cardId;
    @Getter
    @Column(name = "encrypted_payment_password")
    private String encryptedPaymentPassword;
}
