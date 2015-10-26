package dk.unwire.ticketing.core.domain.otp.service;

import dk.unwire.ticketing.core.domain.application.enums.ApplicationPropertyKey;
import dk.unwire.ticketing.core.domain.application.exception.ApplicationPropertyException;
import dk.unwire.ticketing.core.domain.otp.exception.IvsErrorException;
import dk.unwire.ticketing.core.domain.otp.service.model.IvsRequestOtp;
import dk.unwire.ticketing.core.domain.otp.service.model.IvsRequestOtpVO;
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
        Integer ivsContextId = ivsRequestOtpVO.getApplication().getIntProperty(ApplicationPropertyKey.IVS_CONTEXT_ID.getPropertyKey());
        String ivsSenderName = ivsRequestOtpVO.getApplication().getStringProperty(ApplicationPropertyKey.IVS_SENDER_NAME.getPropertyKey());
        String ivsMessageText = ivsRequestOtpVO.getApplication().getStringProperty(ApplicationPropertyKey.IVS_MESSAGE_TEXT.getPropertyKey(), ivsDefaultMessageText);

        validateProperties(ivsRequestOtpVO, ivsContextId, ivsSenderName);

        String url = String.format("%s/context/%d/validation/identity/%d", ivsRequestOtpVO.getSystemProperty().getValue(), ivsContextId, ivsRequestOtpVO.getMsisdn());
        IvsRequestOtp ivsRequestOTP = new IvsRequestOtp(ivsMessageText, ivsSenderName);

        executeRequest(ivsRequestOTP, url);

    }

    private void validateProperties(IvsRequestOtpVO ivsRequestOtpVO, Integer ivsContextId, String ivsSenderName) {
        if (ivsSenderName == null) {
            logger.error("Application with id {} is missing Application property {}", ivsRequestOtpVO.getApplication().getId(), ApplicationPropertyKey.IVS_SENDER_NAME.getPropertyKey());
            throw new ApplicationPropertyException("IVS Sender not defined in application properties");
        } else if (ivsContextId == null) {
            logger.error("Application with id {} is missing Application property {}", ivsRequestOtpVO.getApplication().getId(), ApplicationPropertyKey.IVS_CONTEXT_ID.getPropertyKey());
            throw new ApplicationPropertyException("IVS contextId not defined in application properties");
        }
        logger.debug("Received application properties for application with id [{}] ivs.context.id = [{}] ivs.sender.name = [{}]", ivsRequestOtpVO.getApplication().getId(), ivsContextId, ivsSenderName);
    }

    private void executeRequest(IvsRequestOtp ivsRequestOTP, String url) {
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity entity = new HttpEntity(ivsRequestOTP, headers);
        ResponseEntity<String> responseEntity;
        try {
            responseEntity = restTemplate.exchange(url, HttpMethod.POST, entity, String.class);

        } catch (HttpStatusCodeException e) {
            throw new IvsErrorException(e);
        }
        logger.info("received response from IVS [{}]", responseEntity);
    }
}
