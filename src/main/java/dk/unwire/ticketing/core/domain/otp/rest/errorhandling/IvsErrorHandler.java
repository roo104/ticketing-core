package dk.unwire.ticketing.core.domain.otp.rest.errorhandling;

import dk.unwire.ticketing.core.domain.otp.enums.OtpResponseInfo;
import dk.unwire.ticketing.core.domain.otp.exception.IvsConfirmOtpErrorException;
import dk.unwire.ticketing.core.domain.otp.exception.IvsRequestOtpErrorException;
import dk.unwire.ticketing.spring.rest.common.response.BaseResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.client.HttpStatusCodeException;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
@Order(Ordered.HIGHEST_PRECEDENCE)
public class IvsErrorHandler {

    private static final Logger logger = LoggerFactory.getLogger(IvsErrorHandler.class);

    @ExceptionHandler(IvsRequestOtpErrorException.class)
    public ResponseEntity<BaseResponse> ivsRequestError(HttpServletRequest req, IvsRequestOtpErrorException e) {
        HttpStatusCodeException statusCodeException = (HttpStatusCodeException) e.getCause();
        logger.error("Error received for request [{}] error message [{}]", req.getRequestURI(), statusCodeException.getResponseBodyAsString());

        OtpResponseInfo otpResponseInfo = e.getOtpResponseInfo();
        BaseResponse baseResponse = new BaseResponse(otpResponseInfo);

        return new ResponseEntity(baseResponse, otpResponseInfo.getHttpStatus());

    }

    @ExceptionHandler(IvsConfirmOtpErrorException.class)
    public ResponseEntity<BaseResponse> ivsConfirnmationError(HttpServletRequest req, IvsConfirmOtpErrorException e) {
        HttpStatusCodeException statusCodeException = (HttpStatusCodeException) e.getCause();
        logger.error("Error received for request [{}] error message [{}]", req.getRequestURI(), statusCodeException.getResponseBodyAsString());

        OtpResponseInfo otpResponseInfo = e.getOtpResponseInfo();
        BaseResponse baseResponse = new BaseResponse(otpResponseInfo);

        return new ResponseEntity(baseResponse, otpResponseInfo.getHttpStatus());

    }
}
