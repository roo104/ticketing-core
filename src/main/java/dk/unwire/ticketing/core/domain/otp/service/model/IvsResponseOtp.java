package dk.unwire.ticketing.core.domain.otp.service.model;

public class IvsResponseOtp {
    String serialCode;

    public IvsResponseOtp() {
    }

    public void setSerialCode(String serialCode) {
        this.serialCode = serialCode;
    }

    @Override
    public String toString() {
        return "IvsResponseOtp{" +
                "serialCode='" + this.serialCode + '\'' +
                '}';
    }
}


