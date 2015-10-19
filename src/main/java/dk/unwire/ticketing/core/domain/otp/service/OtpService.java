package dk.unwire.ticketing.core.domain.otp.service;

import dk.unwire.ticketing.core.domain.application.enums.ApplicationPropertyKey;
import dk.unwire.ticketing.core.domain.application.exception.ApplicationPropertyException;
import dk.unwire.ticketing.core.domain.otp.service.model.IVSRequestOTP;
import dk.unwire.ticketing.core.domain.otp.service.model.IVSResponseOTP;
import dk.unwire.ticketing.core.domain.otp.service.model.IvsRequestOtpVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

@Service
@Transactional(rollbackFor = RestClientException.class)
public class OtpService {
    private static final Logger logger = LoggerFactory.getLogger(OtpService.class);
    public static final String EMPTY_STRING = "";

    public ResponseEntity<IVSResponseOTP> requestOtp(IvsRequestOtpVO ivsRequestOtpVO) {

        Integer ivsContextId = ivsRequestOtpVO.getApplication().getIntApplicationProperty(ApplicationPropertyKey.IVS_CONTEXT_ID);
        String ivsSenderName = ivsRequestOtpVO.getApplication().getStringApplicationProperty(ApplicationPropertyKey.IVS_SENDER_NAME);
        String ivsMessageText = ivsRequestOtpVO.getApplication().getStringApplicationProperty(ApplicationPropertyKey.IVS_MESSAGE_TEXT, EMPTY_STRING);

        validateProperties(ivsRequestOtpVO, ivsContextId, ivsSenderName);

        String url = String.format("%s/context/%d/validation/identity/%d", ivsRequestOtpVO.getSystemProperty().getValue(), ivsContextId, ivsRequestOtpVO.getMsisdn());
        IVSRequestOTP ivsRequestOTP = new IVSRequestOTP(ivsMessageText, ivsSenderName);

        ResponseEntity<IVSResponseOTP> response = executeRequest(ivsRequestOTP, url);

        return response;

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

    private ResponseEntity<IVSResponseOTP> executeRequest(IVSRequestOTP ivsRequestOTP, String url) {
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity entity = new HttpEntity(ivsRequestOTP, headers);
        ResponseEntity<IVSResponseOTP> response = null;

        response = restTemplate.exchange(url, HttpMethod.POST, entity, IVSResponseOTP.class);


        return response;
    }
}
