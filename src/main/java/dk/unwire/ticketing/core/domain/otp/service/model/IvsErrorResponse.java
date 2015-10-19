package dk.unwire.ticketing.core.domain.otp.service.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public final class IvsErrorResponse {
    private int errorCode;
    private String message;
    private int secondBeforeRetry;

    @JsonCreator
    public IvsErrorResponse(@JsonProperty("errorCode") int errorCode, @JsonProperty("message") String message, @JsonProperty("secondBeforeRetry") int secondBeforeRetry) {
        this.errorCode = errorCode;
        this.message = message;
        this.secondBeforeRetry = secondBeforeRetry;
    }

    public String getMessage() {
        return this.message;
    }

    public int getErrorCode() {
        return this.errorCode;
    }

    public int getSecondBeforeRetry() {
        return this.secondBeforeRetry;
    }
}
