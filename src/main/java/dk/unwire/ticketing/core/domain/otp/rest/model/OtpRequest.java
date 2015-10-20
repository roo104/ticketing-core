package dk.unwire.ticketing.core.domain.otp.rest.model;


import javax.validation.constraints.Digits;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

public class OtpRequest {
    @NotNull
    @Digits(integer=20, fraction=0)
    @Min(value=0)
    private Long msisdn;

    public void setMsisdn(Long msisdn) {
        this.msisdn = msisdn;
    }

    public Long getMsisdn() {
        return this.msisdn;
    }
}
