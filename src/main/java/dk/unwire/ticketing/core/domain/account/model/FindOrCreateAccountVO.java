package dk.unwire.ticketing.core.domain.account.model;

import dk.unwire.ticketing.spring.rest.common.header.MticketIdentifierType;
import dk.unwire.ticketing.spring.rest.domain.otp.request.vo.OtpConfirmRequestVO;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor(access = AccessLevel.PUBLIC)
public final class FindOrCreateAccountVO {
    @Getter
    private final int applicationId;
    @Getter
    private final String identifier;
    @Getter
    private final MticketIdentifierType identifierType;

    public static FindOrCreateAccountVO fromOtpConfirmRequestVO(OtpConfirmRequestVO otpConfirmRequestVO) {
        return new FindOrCreateAccountVO(
                otpConfirmRequestVO.getApplicationId(),
                otpConfirmRequestVO.getIdentifier(),
                otpConfirmRequestVO.getIdentifierType());
    }
}
