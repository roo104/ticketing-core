package dk.unwire.ticketing.core.config.rest.errorhandler;


import dk.unwire.ticketing.spring.rest.common.helper.RequestIdHelper;
import dk.unwire.ticketing.spring.rest.common.response.BaseResponse;
import dk.unwire.ticketing.spring.rest.common.response.GenericResponseInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@ControllerAdvice
@Order(Ordered.HIGHEST_PRECEDENCE)
public class ValidationErrorMapper {
    private static final Logger logger = LoggerFactory.getLogger(ValidationErrorMapper.class);
    public static final String VALIDATION_ERROR = "Could not validate the request";

    @ExceptionHandler()
    public ResponseEntity<BaseResponse> validationError(MethodArgumentNotValidException ex, HttpServletRequest request) {
        List<FieldError> fieldErrors = ex.getBindingResult().getFieldErrors();
        List<String> errorInfo = new ArrayList<>();

        for (FieldError fieldError : fieldErrors) {
            String errorMessage = String.format("%s %s", fieldError.getField(), fieldError.getDefaultMessage());
            errorInfo.add(errorMessage);
        }
        logger.info("Error parsing [{}] request with id [{}] to [{}] errors: [{}]", request.getMethod(),
                RequestIdHelper.get(request), request.getRequestURI(), String.valueOf(errorInfo));

        GenericResponseInfo authError = GenericResponseInfo.AUTH_ERROR;
        BaseResponse baseResponse = new BaseResponse(authError, VALIDATION_ERROR);
        return new ResponseEntity<>(baseResponse, authError.getHttpStatus());
    }
}
