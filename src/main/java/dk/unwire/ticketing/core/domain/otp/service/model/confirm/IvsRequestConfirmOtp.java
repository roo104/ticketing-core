package dk.unwire.ticketing.core.domain.otp.service.model.confirm;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;


public final class IvsRequestConfirmOtp {
    @Getter
    @JsonProperty("identity")
    private final String identity;
    @Getter
    @JsonProperty("password")
    private final String otp;

    public IvsRequestConfirmOtp(IvsRequestConfirmOtpVO ivsRequestConfirmOtpVO) {
        this.identity = ivsRequestConfirmOtpVO.getMsisdn();
        this.otp = ivsRequestConfirmOtpVO.getOtp();
    }
}
