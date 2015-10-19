package dk.unwire.ticketing.core.domain.otp.service.model;

import dk.unwire.ticketing.core.domain.application.model.Application;
import dk.unwire.ticketing.core.domain.systemproperty.model.SystemProperty;

public class IvsRequestOtpVO {
    private final Application application;
    private final long msisdn;
    private final SystemProperty systemProperty;

    private IvsRequestOtpVO(Builder builder) {
        this.application = builder.application;
        this.msisdn = builder.msisdn;
        this.systemProperty = builder.systemProperty;
    }

    public Application getApplication() {
        return this.application;
    }

    public long getMsisdn() {
        return this.msisdn;
    }

    public SystemProperty getSystemProperty() {
        return this.systemProperty;
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    public static final class Builder {
        private Application application;
        private long msisdn;
        private SystemProperty systemProperty;

        private Builder() {
        }

        public Builder withApplication(Application application) {
            this.application = application;
            return this;
        }

        public Builder withMsisdn(long msisdn) {
            this.msisdn = msisdn;
            return this;
        }

        public Builder withSystemProperty(SystemProperty systemProperty) {
            this.systemProperty = systemProperty;
            return this;
        }

        public IvsRequestOtpVO build() {
            return new IvsRequestOtpVO(this);
        }
    }
}

