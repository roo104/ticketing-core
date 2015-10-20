package dk.unwire.ticketing.core.domain.otp.rest;

import dk.unwire.ticketing.core.domain.otp.application.OtpApplication;
import dk.unwire.ticketing.core.domain.otp.rest.model.OtpRequest;
import dk.unwire.ticketing.core.domain.otp.rest.model.OtpRequestVO;
import dk.unwire.ticketing.spring.rest.common.response.BaseResponse;
import dk.unwire.ticketing.spring.rest.common.response.GenericResponseInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/v1/{applicationId}/otp/")
public class OtpController {
    @Autowired
    public OtpApplication otpApplication;

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<BaseResponse> registerOtp(@PathVariable int applicationId, @RequestBody @Valid OtpRequest otpRequest) {
        this.otpApplication.requestOtp(new OtpRequestVO(otpRequest.getMsisdn(), applicationId));
        GenericResponseInfo responseInfo = GenericResponseInfo.OK;

        return new ResponseEntity<>(new BaseResponse(responseInfo), responseInfo.getHttpStatus());
    }
}
