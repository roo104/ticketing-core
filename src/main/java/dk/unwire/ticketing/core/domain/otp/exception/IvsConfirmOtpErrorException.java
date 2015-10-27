package dk.unwire.ticketing.core.domain.otp.exception;

import dk.unwire.ticketing.core.domain.otp.enums.OtpResponseInfo;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.web.client.HttpStatusCodeException;

public class IvsConfirmOtpErrorException extends RuntimeException {
    @Getter
    private OtpResponseInfo otpResponseInfo;

    public IvsConfirmOtpErrorException(HttpStatusCodeException e) {
        super(e);
        HttpStatus httpStatus = e.getStatusCode();
        switch (httpStatus) {
            case INTERNAL_SERVER_ERROR:
                this.otpResponseInfo = OtpResponseInfo.OTP_VALIDATION_FAILED;
                break;
            case FORBIDDEN:
                this.otpResponseInfo = OtpResponseInfo.OTP_APPINSTANCE_BLOCKED;
                break;
            case BAD_REQUEST:
                this.otpResponseInfo = OtpResponseInfo.OTP_SIGNUP_FAILED;
            case NOT_FOUND:
                this.otpResponseInfo = OtpResponseInfo.OTP_SIGNUP_FAILED;
                break;
            default:
                this.otpResponseInfo = OtpResponseInfo.OTP_VALIDATION_FAILED;
        }
    }
}
