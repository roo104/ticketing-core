package dk.unwire.ticketing.core.domain.application.enums;

import dk.unwire.ticketing.spring.rest.common.response.ResponseInfo;
import org.springframework.http.HttpStatus;

public enum ApplicationResponseInfo implements ResponseInfo {
    APPLICATION_NOT_FOUND(12004, "Application not found", HttpStatus.BAD_REQUEST);

    private int status;
    private String description;
    private HttpStatus httpStatus;

    ApplicationResponseInfo(int status, String description, HttpStatus httpStatus) {
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
