package dk.unwire.ticketing.core.domain.otp.rest.model;


import javax.validation.constraints.NotNull;

public class OtpRequest {
    @NotNull
    private Long msisdn;

    public void setMsisdn(Long msisdn) {
        this.msisdn = msisdn;
    }

    public Long getMsisdn() {
        return this.msisdn;
    }
}

