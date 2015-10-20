package dk.unwire.ticketing.core.domain.otp.exception;

import dk.unwire.ticketing.core.domain.otp.enums.OtpResponseInfo;
import org.springframework.http.HttpStatus;
import org.springframework.web.client.HttpStatusCodeException;

public final class IvsErrorException extends RuntimeException {
    private OtpResponseInfo otpResponseInfo;

    public IvsErrorException(HttpStatusCodeException e) {
        super(e);
        HttpStatus httpStatus = e.getStatusCode();
        switch (httpStatus) {
            case INTERNAL_SERVER_ERROR:
                this.otpResponseInfo = OtpResponseInfo.OTP_VALIDATION_FAILED;

            case FORBIDDEN:
                this.otpResponseInfo = OtpResponseInfo.OTP_MSISDN_BLOCKED;

            case BAD_REQUEST:
                this.otpResponseInfo = OtpResponseInfo.OTP_SIGNUP_FAILED;

            default:
                this.otpResponseInfo = OtpResponseInfo.OTP_VALIDATION_FAILED;
        }

    }

    public OtpResponseInfo getOtpResponseInfo() {
        return this.otpResponseInfo;
    }
}
