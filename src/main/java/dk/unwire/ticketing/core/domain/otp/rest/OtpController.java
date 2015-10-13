package dk.unwire.ticketing.core.domain.otp.rest;

import dk.unwire.ticketing.core.domain.otp.rest.model.OtpRequest;
import dk.unwire.ticketing.spring.rest.common.response.BaseResponse;
import dk.unwire.ticketing.spring.rest.common.response.StatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/v1/{applicationId}/otp/")
public class OtpController {
    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<BaseResponse> registerOtp(@PathVariable int applicationId, @RequestBody @Valid OtpRequest otpRequest) {

        StatusCode statusCode = StatusCode.OK;

        return new ResponseEntity<>(new BaseResponse(statusCode), statusCode.getHttpStatus());
    }
}
