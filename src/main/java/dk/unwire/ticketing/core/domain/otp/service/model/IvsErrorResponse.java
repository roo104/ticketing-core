package dk.unwire.ticketing.core.domain.otp.service.model;

public class IvsErrorResponse{
    private int errorCode;
    private String message;
    private int secondBeforeRetry;

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setSecondBeforeRetry(int secondBeforeRetry) {
        this.secondBeforeRetry = secondBeforeRetry;
    }

    public String getMessage() {
        return this.message;
    }

}
