package dk.unwire.ticketing.core.domain.otp.model;

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

    public OtpConfirmRequestVO(String otp, String identifier, int applicationId) {
        this.otp = otp;
        this.identifier = identifier;
        this.applicationId = applicationId;
        this.identifierType = IdentifierType.MSISDN;
    }
}
