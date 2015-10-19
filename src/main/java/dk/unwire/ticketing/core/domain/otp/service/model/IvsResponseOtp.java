package dk.unwire.ticketing.core.domain.otp.service.model;

public final class IvsResponseOtp {
    String serialCode;

    public IvsResponseOtp(String serialCode) {
        this.serialCode = serialCode;
    }

    @Override
    public String toString() {
        return "IvsResponseOtp{" +
                "serialCode='" + this.serialCode + '\'' +
                '}';
    }
}


