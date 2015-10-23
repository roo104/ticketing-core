package dk.unwire.ticketing.core.domain.otp.service.model.confirm;

import dk.unwire.ticketing.core.domain.application.model.Application;
import dk.unwire.ticketing.core.domain.systemproperty.model.SystemProperty;
import lombok.Builder;
import lombok.Getter;

@Builder
public final class IvsRequestConfirmOtpVO {
    @Getter
    private final Application application;
    @Getter
    private final String msisdn;
    @Getter
    private final SystemProperty systemProperty;
    @Getter
    private final String otp;
}
