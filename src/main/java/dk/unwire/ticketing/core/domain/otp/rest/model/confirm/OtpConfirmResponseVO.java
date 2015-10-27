package dk.unwire.ticketing.core.domain.otp.rest.model.confirm;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public final class OtpConfirmResponseVO {
    @Getter
    private final String identifier;
    @Getter
    private final int accountId;

}
