package dk.unwire.ticketing.core.domain.otp.rest.model.confirm;

import dk.unwire.ticketing.core.domain.account.enums.IdentifierType;
import lombok.Getter;

public final class OtpConfirmRequestVO {
    @Getter
    private final int applicationId;
    @Getter
    private final String otp;
    @Getter
    private final String identifier;
    @Getter
    private final IdentifierType identifierType;

    public OtpConfirmRequestVO(OtpConfirmRequest otpConfirmRequest, int applicationId) {
        this.otp = otpConfirmRequest.getOtp();
        this.identifier = otpConfirmRequest.getMsisdn();
        this.applicationId = applicationId;
        this.identifierType = IdentifierType.MSISDN;
    }
}
