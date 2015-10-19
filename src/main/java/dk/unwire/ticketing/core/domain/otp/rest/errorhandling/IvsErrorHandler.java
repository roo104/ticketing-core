package dk.unwire.ticketing.core.domain.otp.rest.errorhandling;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.deser.Deserializers;
import dk.unwire.ticketing.core.domain.otp.service.model.IvsErrorResponse;
import dk.unwire.ticketing.spring.rest.common.response.BaseResponse;
import dk.unwire.ticketing.spring.rest.common.response.StatusCode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.client.HttpStatusCodeException;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@ControllerAdvice
@Order(Ordered.HIGHEST_PRECEDENCE)
public class IvsErrorHandler {
    @Autowired
    private ObjectMapper objectMapper;
    private static final Logger logger = LoggerFactory.getLogger(IvsErrorHandler.class);


    @ExceptionHandler(HttpStatusCodeException.class)
    public ResponseEntity<Deserializers.Base> handleHttpError(HttpServletRequest req, HttpStatusCodeException e) {

        logger.error("HttpError [{}] received for request [{}] error message [{}]", e.getStatusCode(), req.getRequestURI(), e.getResponseBodyAsString());

        IvsErrorResponse ivsErrorResponse = null;
        try {
            ivsErrorResponse = this.objectMapper.readValue(e.getResponseBodyAsString(), IvsErrorResponse.class);
        } catch (IOException exception) {
            logger.error("Could not parse IVS error response [{}]", exception.getMessage());
        }
        BaseResponse baseResponse = new BaseResponse(StatusCode.AUTH_ERROR, "IVS error: " + ivsErrorResponse.getMessage());

        return new ResponseEntity(baseResponse, e.getStatusCode());

    }
}
