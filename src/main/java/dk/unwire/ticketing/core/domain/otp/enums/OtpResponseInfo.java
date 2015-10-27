package dk.unwire.ticketing.core.domain.otp.enums;

import dk.unwire.ticketing.spring.rest.common.response.ResponseInfo;
import org.springframework.http.HttpStatus;

public enum OtpResponseInfo implements ResponseInfo {
    OTP_VALIDATION_FAILED(422021, "OTP validation failed.", HttpStatus.BAD_REQUEST),
    OTP_SIGNUP_FAILED(422022, "Signup failed. Please restart requesting an new OTP", HttpStatus.INTERNAL_SERVER_ERROR),
    OTP_APPINSTANCE_BLOCKED(422023, "Requester (App Instance) blocked due to excessive signup attempts", HttpStatus.FORBIDDEN),
    OTP_MSISDN_BLOCKED(422024, "MSISDN blocked due to excessive signup attempts", HttpStatus.FORBIDDEN),
    OTP_REQUESTER_BLOCKED(422013, "Requester (App Instance/remote IP address ) blocked due to excessive OTP requests", HttpStatus.FORBIDDEN),
    OTP_MSISDN_INVALID(422011, "MSISDN invalid for OTP request", HttpStatus.BAD_REQUEST),
    OTP_REQUEST_DEFAULT_ERROR(422014, "Error occurred when requesting OTP", HttpStatus.INTERNAL_SERVER_ERROR),
    OTP_REQUEST_SMS_NOT_SENT(422012, "SMS delivery failed. Please try again later.", HttpStatus.SERVICE_UNAVAILABLE);


    private int status;
    private String description;
    private HttpStatus httpStatus;

    OtpResponseInfo(int status, String description, HttpStatus httpStatus) {
        this.status = status;
        this.description = description;
        this.httpStatus = httpStatus;
    }


    @Override
    public int getStatus() {
        return this.status;
    }

    @Override
    public String getDescription() {
        return this.description;
    }

    @Override
    public HttpStatus getHttpStatus() {
        return this.httpStatus;
    }
}
