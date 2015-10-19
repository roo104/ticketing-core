package dk.unwire.ticketing.core.domain.application.rest.errorhandling;

import dk.unwire.ticketing.core.domain.application.exception.ApplicationNotFoundException;
import dk.unwire.ticketing.spring.rest.common.helper.RequestIdHelper;
import dk.unwire.ticketing.spring.rest.common.response.BaseResponse;
import dk.unwire.ticketing.spring.rest.common.response.StatusCode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
@Order(Ordered.HIGHEST_PRECEDENCE)
public class ApplicationExceptionHandler {
    private static final Logger logger = LoggerFactory.getLogger(ApplicationExceptionHandler.class);

    @ExceptionHandler({ApplicationNotFoundException.class})
    @ResponseBody
    protected ResponseEntity<BaseResponse> argumentError(ApplicationNotFoundException e, HttpServletRequest request) {
        logger.info("Error validating [{}] request with id [{}] to [{}] error: [{}]", request.getMethod(),
                RequestIdHelper.get(request), request.getRequestURI(), e.getMessage());
        BaseResponse baseResponse = new BaseResponse(StatusCode.AUTH_ERROR, e.getMessage());

        return new ResponseEntity<>(baseResponse, StatusCode.AUTH_ERROR.getHttpStatus());
    }
}

