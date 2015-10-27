package dk.unwire.ticketing.core.domain.otp.service.model.register;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@ToString
public final class IvsRequestOtp {
    @Getter
    private final SenderVO sender;
    @Getter
    private final String message;

    public IvsRequestOtp(String sender, String message) {
        this.message = message;
        this.sender = new SenderVO(sender);
    }

    @AllArgsConstructor
    @ToString
    private final class SenderVO {
        @Getter
        private final String name;


    }
}
