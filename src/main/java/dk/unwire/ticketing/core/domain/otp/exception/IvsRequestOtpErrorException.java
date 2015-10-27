package dk.unwire.ticketing.core.domain.otp.exception;

import dk.unwire.ticketing.core.domain.otp.enums.OtpResponseInfo;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.web.client.HttpStatusCodeException;

public final class IvsRequestOtpErrorException extends RuntimeException {
    @Getter
    private OtpResponseInfo otpResponseInfo;

    public IvsRequestOtpErrorException(HttpStatusCodeException e) {
        super(e);
        HttpStatus httpStatus = e.getStatusCode();
        switch (httpStatus) {
            case INTERNAL_SERVER_ERROR:
                this.otpResponseInfo = OtpResponseInfo.OTP_REQUEST_SMS_NOT_SENT;
                break;
            case FORBIDDEN:
                this.otpResponseInfo = OtpResponseInfo.OTP_MSISDN_BLOCKED;
                break;
            case BAD_REQUEST:
                this.otpResponseInfo = OtpResponseInfo.OTP_REQUEST_DEFAULT_ERROR;
                break;
            default:
                this.otpResponseInfo = OtpResponseInfo.OTP_REQUEST_DEFAULT_ERROR;
        }
    }
}
