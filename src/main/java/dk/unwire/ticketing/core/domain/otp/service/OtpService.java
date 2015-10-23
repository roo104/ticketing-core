package dk.unwire.ticketing.core.domain.otp.service;

import dk.unwire.ticketing.core.domain.application.enums.ApplicationPropertyKey;
import dk.unwire.ticketing.core.domain.application.exception.ApplicationPropertyException;
import dk.unwire.ticketing.core.domain.otp.exception.IvsConfirmOtpErrorException;
import dk.unwire.ticketing.core.domain.otp.exception.IvsRequestOtpErrorException;
import dk.unwire.ticketing.core.domain.otp.service.model.confirm.IvsRequestConfirmOtp;
import dk.unwire.ticketing.core.domain.otp.service.model.confirm.IvsRequestConfirmOtpVO;
import dk.unwire.ticketing.core.domain.otp.service.model.register.IvsRequestOtp;
import dk.unwire.ticketing.core.domain.otp.service.model.register.IvsRequestOtpVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;


@Service
@Transactional(rollbackFor = RestClientException.class)
public class OtpService {
    private static final Logger logger = LoggerFactory.getLogger(OtpService.class);

    public void requestOtp(IvsRequestOtpVO ivsRequestOtpVO) {
        String ivsDefaultMessageText = "";
        int applicationId = ivsRequestOtpVO.getApplication().getId();
        Integer ivsContextId = ivsRequestOtpVO.getApplication().getIntApplicationProperty(ApplicationPropertyKey.IVS_CONTEXT_ID);
        String ivsSenderName = ivsRequestOtpVO.getApplication().getStringApplicationProperty(ApplicationPropertyKey.IVS_SENDER_NAME);
        String ivsMessageText = ivsRequestOtpVO.getApplication().getStringApplicationProperty(ApplicationPropertyKey.IVS_MESSAGE_TEXT, ivsDefaultMessageText);

        validateIvsContextIdProperty(applicationId, ivsContextId);
        validateIvsSenderProperty(applicationId, ivsSenderName);
        logger.debug("Received application properties for application with id [{}] ivs.context.id = [{}] ivs.sender.name = [{}]", applicationId, ivsContextId, ivsSenderName);

        String url = String.format("%s/context/%d/validation/identity/%s", ivsRequestOtpVO.getSystemProperty().getValue(), ivsContextId, ivsRequestOtpVO.getMsisdn());
        IvsRequestOtp ivsRequestOTP = new IvsRequestOtp(ivsSenderName, ivsMessageText);

        try {
            executeRequest(ivsRequestOTP, url);
        } catch (HttpStatusCodeException e) {
            logger.info("Error requesting OTP from IVS {}", e.getResponseBodyAsString());
            throw new IvsRequestOtpErrorException(e);
        }
    }

    public void confirmOtp(IvsRequestConfirmOtpVO ivsRequestConfirmOtpVO) {
        int applicationId = ivsRequestConfirmOtpVO.getApplication().getId();

        Integer ivsContextId = ivsRequestConfirmOtpVO.getApplication().getIntApplicationProperty(ApplicationPropertyKey.IVS_CONTEXT_ID);

        validateIvsContextIdProperty(applicationId, ivsContextId);

        String url = String.format("%s/context/%d/confirmation/", ivsRequestConfirmOtpVO.getSystemProperty().getValue(), ivsContextId);
        IvsRequestConfirmOtp ivsRequestConfirmOtp = new IvsRequestConfirmOtp(ivsRequestConfirmOtpVO);
        try {
            executeRequest(ivsRequestConfirmOtp, url);
        } catch (HttpStatusCodeException e) {
            logger.info("Error confirming OTP from IVS {}", e.getResponseBodyAsString());

            throw new IvsConfirmOtpErrorException(e);
        }
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

    private void executeRequest(Object object, String url) {
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity entity = new HttpEntity(object, headers);
        ResponseEntity<String> responseEntity;

        responseEntity = restTemplate.exchange(url, HttpMethod.POST, entity, String.class);

        logger.info("received response from IVS [{}]", responseEntity);
    }
}
