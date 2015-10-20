package dk.unwire.ticketing.core.domain.otp.rest.errorhandling;

import com.fasterxml.jackson.databind.ObjectMapper;
import dk.unwire.ticketing.core.domain.otp.enums.OtpResponseInfo;
import dk.unwire.ticketing.core.domain.otp.exception.IvsErrorException;
import dk.unwire.ticketing.spring.rest.common.response.BaseResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.HttpStatusCodeException;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
@Order(Ordered.HIGHEST_PRECEDENCE)
public class IvsErrorHandler {
    @Autowired
    private ObjectMapper objectMapper;
    private static final Logger logger = LoggerFactory.getLogger(IvsErrorHandler.class);

    @ExceptionHandler(IvsErrorException.class)
    @ResponseBody
    public ResponseEntity<BaseResponse> handleHttpError(HttpServletRequest req, IvsErrorException e) {
        HttpStatusCodeException statusCodeException = (HttpStatusCodeException) e.getCause();
        logger.error("Error received for request [{}] error message [{}]", req.getRequestURI(), statusCodeException.getResponseBodyAsString());

        OtpResponseInfo otpResponseInfo = e.getOtpResponseInfo();
        BaseResponse baseResponse = new BaseResponse(otpResponseInfo);

        return new ResponseEntity(baseResponse, otpResponseInfo.getHttpStatus());

    }
}
