package dk.unwire.ticketing.core.domain.otp.rest;

import dk.unwire.ticketing.core.domain.account.model.Account;
import dk.unwire.ticketing.core.domain.account.model.AccountIdentifier;
import dk.unwire.ticketing.core.domain.otp.application.OtpApplication;
import dk.unwire.ticketing.core.domain.otp.rest.model.register.OtpRequest;
import dk.unwire.ticketing.spring.rest.common.response.BaseResponse;
import dk.unwire.ticketing.spring.rest.common.response.GenericResponseInfo;
import dk.unwire.ticketing.spring.rest.common.response.StatusResponse;
import dk.unwire.ticketing.spring.rest.domain.otp.request.OTPConfirmationRequest;
import dk.unwire.ticketing.spring.rest.domain.otp.request.vo.OtpConfirmRequestVO;
import dk.unwire.ticketing.spring.rest.domain.otp.response.OTPConfirmationResponse;
import dk.unwire.ticketing.spring.rest.domain.otp.response.vo.OtpConfirmResponseVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/v1/{applicationId}/otp/")
public class OtpController {

	private static final int OK_STATUS_CODE = 11001;

	@Autowired
    public OtpApplication otpApplication;

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<BaseResponse> registerOtp(@PathVariable int applicationId,
													@RequestBody @Valid OtpRequest otpRequest) {
        this.otpApplication.requestOtp(otpRequest.generateOtpRequestVO(applicationId));
        GenericResponseInfo responseInfo = GenericResponseInfo.OK;

        return new ResponseEntity<>(new BaseResponse(responseInfo), responseInfo.getHttpStatus());
    }

    @RequestMapping(path = "confirm/", method = RequestMethod.PUT)
    public ResponseEntity<OTPConfirmationResponse> confirmOtp(@PathVariable int applicationId,
															  @RequestBody @Valid OTPConfirmationRequest otpConfirmRequest) {

		OtpConfirmRequestVO otpConfirmRequestVO = otpConfirmRequest.generateOtpConfirmRequestVO(applicationId);
        AccountIdentifier accountIdentifier = this.otpApplication.confirmOtp(otpConfirmRequestVO);

        Account account = accountIdentifier.getAccount();
		OtpConfirmResponseVO otpConfirmResponseVO =
				new OtpConfirmResponseVO(accountIdentifier.getIdentifier(), account.getId());
        StatusResponse statusResponse = new StatusResponse(OK_STATUS_CODE, "OTP Confirmed");
		OTPConfirmationResponse response = new OTPConfirmationResponse(statusResponse, otpConfirmResponseVO);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
