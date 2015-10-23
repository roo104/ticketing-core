package dk.unwire.ticketing.core.domain.otp.rest.model.register;


import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.NotNull;

public class OtpRequest {
    @NotNull
    @NotEmpty
    @Setter
    @Getter
    private String msisdn;


}
