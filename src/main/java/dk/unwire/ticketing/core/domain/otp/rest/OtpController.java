package dk.unwire.ticketing.core.domain.otp.rest;

import dk.unwire.ticketing.core.domain.account.model.AccountIdentifier;
import dk.unwire.ticketing.core.domain.otp.application.OtpApplication;
import dk.unwire.ticketing.core.domain.otp.rest.model.confirm.OtpConfirmRequest;
import dk.unwire.ticketing.core.domain.otp.rest.model.confirm.OtpConfirmResponse;
import dk.unwire.ticketing.core.domain.otp.rest.model.confirm.OtpConfirmResponseVO;
import dk.unwire.ticketing.core.domain.otp.rest.model.register.OtpRequest;
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
        this.otpApplication.requestOtp(otpRequest.generateOtpRequestVO(applicationId));
        GenericResponseInfo responseInfo = GenericResponseInfo.OK;

        return new ResponseEntity<>(new BaseResponse(responseInfo), responseInfo.getHttpStatus());
    }

    @RequestMapping(path = "confirm/", method = RequestMethod.PUT)
    public ResponseEntity<OtpConfirmResponse> confirmOtp(@PathVariable int applicationId, @RequestBody @Valid OtpConfirmRequest otpConfirmRequest) {
        AccountIdentifier accountIdentifier = this.otpApplication.confirmOtp(otpConfirmRequest.generateOtpConfirmRequestVO(applicationId));

        OtpConfirmResponseVO otpConfirmResponseVO = new OtpConfirmResponseVO(accountIdentifier.getIdentifier(), accountIdentifier.getAccount().getId());
        GenericResponseInfo responseInfo = GenericResponseInfo.OK;
        OtpConfirmResponse response = new OtpConfirmResponse(responseInfo, otpConfirmResponseVO);

        return new ResponseEntity<>(response, responseInfo.getHttpStatus());
    }
}
