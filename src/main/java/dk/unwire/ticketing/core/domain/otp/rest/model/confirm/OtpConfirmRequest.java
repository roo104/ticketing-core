package dk.unwire.ticketing.core.domain.otp.rest.model.confirm;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.NotNull;

public final class OtpConfirmRequest {
    @NotNull
    @NotEmpty
    @Getter
    private String msisdn;
    @NotEmpty
    @Getter
    private String otp;

    @JsonCreator
    public OtpConfirmRequest(@JsonProperty("msisdn") String msisdn, @JsonProperty("otp") String otp) {
        this.msisdn = msisdn;
        this.otp = otp;

    }
}
