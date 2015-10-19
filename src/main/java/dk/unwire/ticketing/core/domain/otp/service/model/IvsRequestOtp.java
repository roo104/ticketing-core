package dk.unwire.ticketing.core.domain.otp.service.model;

public final class IvsRequestOtp {
    private final String message;
    private final SenderVO sender;

    public IvsRequestOtp(String message, String sender) {
        this.message = message;
        this.sender = new SenderVO(sender);

    }

    public String getMessage() {
        return this.message;
    }

    public SenderVO getSender() {
        return this.sender;
    }

    private final class SenderVO {
        private final String name;

        public SenderVO(String name) {
            this.name = name;
        }

        public String getName() {
            return this.name;
        }
    }
}
