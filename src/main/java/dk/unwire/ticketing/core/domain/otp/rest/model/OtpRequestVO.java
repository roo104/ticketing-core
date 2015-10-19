package dk.unwire.ticketing.core.domain.otp.rest.model;

public final class OtpRequestVO {
    private final int applicationId;
    private final Long msisdn;

    public OtpRequestVO(Long msisdn, int applicationId) {
        this.msisdn = msisdn;
        this.applicationId = applicationId;
    }

    public int getApplicationId() {
        return this.applicationId;
    }

    public Long getMsisdn() {
        return this.msisdn;
    }

    @Override
    public String toString() {
        return "OtpRequestVO{" +
                "applicationId=" + this.applicationId +
                ", msisdn=" + this.msisdn +
                '}';
    }
}
