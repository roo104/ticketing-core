package dk.unwire.ticketing.core.domain.otp.rest.model.register;

import lombok.Getter;
import lombok.RequiredArgsConstructor;


@RequiredArgsConstructor
public final class OtpRequestVO {
    @Getter
    private final String msisdn;
    @Getter
    private final int applicationId;

}
