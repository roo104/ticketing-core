package dk.unwire.ticketing.core.domain.otp.rest.model.confirm;

import com.fasterxml.jackson.annotation.JsonProperty;
import dk.unwire.ticketing.spring.rest.common.response.BaseResponse;
import dk.unwire.ticketing.spring.rest.common.response.ResponseInfo;
import lombok.Getter;

public final class OtpConfirmResponse extends BaseResponse {
    @Getter
    @JsonProperty("account")
    private final OtpConfirmResponseVO otpConfirmResponseVO;

    public OtpConfirmResponse(ResponseInfo responseInfoCode, OtpConfirmResponseVO otpConfirmResponseVO) {
        super(responseInfoCode);
        this.otpConfirmResponseVO = otpConfirmResponseVO;

    }
}
