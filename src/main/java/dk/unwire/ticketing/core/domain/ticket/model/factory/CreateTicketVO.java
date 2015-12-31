package dk.unwire.ticketing.core.domain.ticket.model.factory;

import dk.unwire.ticketing.core.domain.account.model.Account;
import dk.unwire.ticketing.core.domain.product.model.Product;
import dk.unwire.ticketing.core.domain.ticket.model.vo.PaymentType;
import dk.unwire.ticketing.core.domain.ticket.model.vo.TicketIssuingType;
import lombok.Getter;

import java.util.Collection;

public class CreateTicketVO {

    @Getter
    private final TicketIssuingType ticketIssuingType;
    @Getter
    private final Account account;
    @Getter
    private final Collection<Product> products;
    @Getter
    private final PaymentType paymentType;

    private CreateTicketVO(Builder builder) {
        this.ticketIssuingType = builder.ticketIssuingType;
        this.account = builder.account;
        this.products = builder.products;
        this.paymentType = builder.paymentType;
    }

    public static FirstStep newBuilder() {
        return new Builder();
    }

    public interface FirstStep {
        SecondStep ticketIssuingType(TicketIssuingType ticketIssuingType);
    }

    public interface SecondStep {
        ThrirdStep products(Collection<Product> products);
    }

    public interface ThrirdStep {
        FourthStep paymentType(PaymentType paymentType);
    }

    public interface FourthStep {
        FinalStep account(Account account);
    }

    public interface FinalStep {
        CreateTicketVO build();
    }


    private static final class Builder implements FirstStep, SecondStep, ThrirdStep, FourthStep, FinalStep {
        private TicketIssuingType ticketIssuingType;
        private Account account;
        private Collection<Product> products;
        private PaymentType paymentType;

        private Builder() {
        }

        @Override
        public SecondStep ticketIssuingType(TicketIssuingType ticketIssuingType) {
            this.ticketIssuingType = ticketIssuingType;
            return this;
        }

        @Override
        public ThrirdStep products(Collection<Product> products) {
            this.products = products;
            return this;
        }

        @Override
        public FourthStep paymentType(PaymentType paymentType) {
            this.paymentType = paymentType;
            return this;
        }

        @Override
        public FinalStep account(Account account) {
            this.account = account;
            return this;
        }

        @Override
        public CreateTicketVO build() {
            return new CreateTicketVO(this);
        }
    }
}
