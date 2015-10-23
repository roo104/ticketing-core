package dk.unwire.ticketing.core.domain.otp.service.model.register;

import dk.unwire.ticketing.core.domain.application.model.Application;
import dk.unwire.ticketing.core.domain.systemproperty.model.SystemProperty;
import lombok.Builder;
import lombok.Getter;

@Builder
public final class IvsRequestOtpVO {
    @Getter
    private final Application application;
    @Getter
    private final String msisdn;
    @Getter
    private final SystemProperty systemProperty;


}

