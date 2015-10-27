package dk.unwire.ticketing.core.domain.otp.service.model.register;

import dk.unwire.ticketing.core.domain.application.enums.ApplicationPropertyKey;
import dk.unwire.ticketing.core.domain.application.exception.ApplicationPropertyException;
import dk.unwire.ticketing.core.domain.application.model.Application;
import lombok.Builder;
import lombok.Getter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Builder
public final class IvsRequestOtpVO {
    @Getter
    private final Application application;
    @Getter
    private final String msisdn;
    @Getter
    private final String baseUrl;

    private static final Logger logger = LoggerFactory.getLogger(IvsRequestOtpVO.class);

    public void validateProperties(String ivsSenderName, Integer ivsContextId) {
        validateIvsContextIdProperty(this.application.getId(), ivsContextId);
        validateIvsSenderProperty(this.application.getId(), ivsSenderName);
    }

    private void validateIvsContextIdProperty(int applicationId, Integer ivsContextId) {
        if (ivsContextId == null) {
            logger.error("Application with id {} is missing Application property {}", applicationId, ApplicationPropertyKey.IVS_CONTEXT_ID.getPropertyKey());
            throw new ApplicationPropertyException("IVS contextId not defined in application properties");
        }
    }

    private void validateIvsSenderProperty(int applicationId, String ivsSenderName) {
        if (ivsSenderName == null) {
            logger.error("Application with id {} is missing Application property {}", applicationId, ApplicationPropertyKey.IVS_SENDER_NAME.getPropertyKey());
            throw new ApplicationPropertyException("IVS Sender not defined in application properties");
        }
    }
}

