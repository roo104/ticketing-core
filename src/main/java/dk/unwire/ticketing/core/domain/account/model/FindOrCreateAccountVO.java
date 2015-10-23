package dk.unwire.ticketing.core.domain.account.model;

import dk.unwire.ticketing.core.domain.account.enums.IdentifierType;
import dk.unwire.ticketing.core.domain.otp.rest.model.confirm.OtpConfirmRequestVO;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
public final class FindOrCreateAccountVO {
    @Getter
    private final int applicationId;
    @Getter
    private final String identifier;
    @Getter
    private final IdentifierType identifierType;

    public static FindOrCreateAccountVO fromOtpConfirmRequestVO(OtpConfirmRequestVO otpConfirmRequestVO) {
        return new FindOrCreateAccountVO(
                otpConfirmRequestVO.getApplicationId(),
                otpConfirmRequestVO.getIdentifier(),
                otpConfirmRequestVO.getIdentifierType());
    }
}
