package dk.unwire.ticketing.core.domain.otp.service.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public final class IvsResponseOtp {
    String serialCode;
    @JsonCreator
    public IvsResponseOtp(@JsonProperty("errorcode")String serialCode) {
        this.serialCode = serialCode;
    }

    @Override
    public String toString() {
        return "IvsResponseOtp{" +
                "serialCode='" + this.serialCode + '\'' +
                '}';
    }
}


