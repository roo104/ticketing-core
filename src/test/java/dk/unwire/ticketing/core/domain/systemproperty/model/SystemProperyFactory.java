package dk.unwire.ticketing.core.domain.systemproperty.model;

import dk.unwire.ticketing.core.domain.otp.OtpConstants;

public final class SystemProperyFactory {
    public static SystemProperty getTestSystemProperty() {
        SystemProperty systemProperty = new SystemProperty("BaseUrl", OtpConstants.IVS_BASE_URL);
        return systemProperty;
    }
}
