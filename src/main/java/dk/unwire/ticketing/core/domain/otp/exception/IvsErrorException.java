package dk.unwire.ticketing.core.domain.otp.exception;

import dk.unwire.ticketing.core.domain.otp.enums.OtpResponseInfo;

public final class IvsErrorException extends RuntimeException {
    private final OtpResponseInfo otpResponseInfo;

    public IvsErrorException(Throwable cause, OtpResponseInfo otpResponseInfo) {
        super(otpResponseInfo.getDescription(), cause);
        this.otpResponseInfo = otpResponseInfo;
    }

    public OtpResponseInfo getOtpResponseInfo() {
        return this.otpResponseInfo;
    }
}
