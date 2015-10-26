package dk.unwire.ticketing.core.domain.otp.service.model.confirm;

import dk.unwire.ticketing.core.domain.application.enums.ApplicationPropertyKey;
import dk.unwire.ticketing.core.domain.application.exception.ApplicationPropertyException;
import dk.unwire.ticketing.core.domain.application.model.Application;
import lombok.Builder;
import lombok.Getter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Builder
public final class IvsRequestConfirmOtpVO {
    @Getter
    private final Application application;
    @Getter
    private final String msisdn;
    @Getter
    private final String baseUrl;
    @Getter
    private final String otp;

    private static final Logger logger = LoggerFactory.getLogger(IvsRequestConfirmOtpVO.class);

    public void validateProperties(Integer ivsContextId) {
        validateIvsContextIdProperty(this.application.getId(), ivsContextId);
    }

    private void validateIvsContextIdProperty(int applicationId, Integer ivsContextId) {
        if (ivsContextId == null) {
            logger.error("Application with id {} is missing Application property {}", applicationId, ApplicationPropertyKey.IVS_CONTEXT_ID.getPropertyKey());
            throw new ApplicationPropertyException("IVS contextId not defined in application properties");
        }
    }
}
