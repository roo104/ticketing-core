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

    private static final Logger logger = LoggerFactory.getLogger(IvsRequestOtpVO.class);

    @Getter
    private final Application application;
    @Getter
    private final String msisdn;
    @Getter
    private final String baseUrl;

    public void validateProperties(String ivsSenderName, Integer ivsContextId) {
        validateIvsContextIdProperty(ivsContextId);
        validateIvsSenderProperty(ivsSenderName);
    }

    private void validateIvsContextIdProperty(Integer ivsContextId) {
        if (ivsContextId == null) {
            logger.error("Application with id {} is missing Application property {}", this.application.getId(),
                    ApplicationPropertyKey.IVS_CONTEXT_ID.getKey());
            throw new ApplicationPropertyException("IVS contextId not defined in application properties");
        }
    }

    private void validateIvsSenderProperty(String ivsSenderName) {
        if (ivsSenderName == null) {
            logger.error("Application with id {} is missing Application property {}", this.application.getId(),
                    ApplicationPropertyKey.IVS_SENDER_NAME.getKey());
            throw new ApplicationPropertyException("IVS Sender not defined in application properties");
        }
    }
}

