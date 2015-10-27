package dk.unwire.ticketing.core.domain.otp.rest.model.register;


import dk.unwire.ticketing.core.domain.otp.model.OtpRequestVO;
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

    public OtpRequestVO generateOtpRequestVO(int applicationId) {
        return new OtpRequestVO(this.msisdn, applicationId);
    }
}
