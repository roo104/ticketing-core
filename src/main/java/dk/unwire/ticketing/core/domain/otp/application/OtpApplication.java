package dk.unwire.ticketing.core.domain.otp.application;

import dk.unwire.ticketing.core.domain.account.model.AccountIdentifier;
import dk.unwire.ticketing.core.domain.account.model.FindOrCreateAccountVO;
import dk.unwire.ticketing.core.domain.account.service.AccountService;
import dk.unwire.ticketing.core.domain.application.model.Application;
import dk.unwire.ticketing.core.domain.application.service.ApplicationService;
import dk.unwire.ticketing.core.domain.otp.rest.model.confirm.OtpConfirmRequestVO;
import dk.unwire.ticketing.core.domain.otp.rest.model.register.OtpRequestVO;
import dk.unwire.ticketing.core.domain.otp.service.OtpService;
import dk.unwire.ticketing.core.domain.otp.service.model.confirm.IvsRequestConfirmOtpVO;
import dk.unwire.ticketing.core.domain.otp.service.model.register.IvsRequestOtpVO;
import dk.unwire.ticketing.core.domain.systemproperty.enums.SystemPropertyEnum;
import dk.unwire.ticketing.core.domain.systemproperty.model.SystemProperty;
import dk.unwire.ticketing.core.domain.systemproperty.service.SystemPropertyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@Transactional
public class OtpApplication {
    @Autowired
    private ApplicationService applicationService;
    @Autowired
    private OtpService otpService;
    @Autowired
    private SystemPropertyService systemPropertyService;
    @Autowired
    private AccountService accountService;

    public void requestOtp(OtpRequestVO requestVO) {
        Application application = validateApplication(requestVO.getApplicationId());
        SystemProperty baseUrlProperty = getSystemProperty(SystemPropertyEnum.IVS_BASEURL);

        IvsRequestOtpVO ivsRequestOtpVO = IvsRequestOtpVO.builder()
                .application(application)
                .msisdn(requestVO.getMsisdn())
                .systemProperty(baseUrlProperty)
                .build();

        this.otpService.requestOtp(ivsRequestOtpVO);
    }

    public AccountIdentifier confirmOtp(OtpConfirmRequestVO otpConfirmRequestVO) {
        Application application = validateApplication(otpConfirmRequestVO.getApplicationId());
        SystemProperty baseUrlProperty = getSystemProperty(SystemPropertyEnum.IVS_BASEURL);

        IvsRequestConfirmOtpVO ivsRequestConfirmOtpVO = IvsRequestConfirmOtpVO.builder()
                .application(application)
                .msisdn(otpConfirmRequestVO.getIdentifier())
                .systemProperty(baseUrlProperty)
                .otp(otpConfirmRequestVO.getOtp())
                .build();
        this.otpService.confirmOtp(ivsRequestConfirmOtpVO);

        FindOrCreateAccountVO findOrCreateAccountVO = FindOrCreateAccountVO.fromOtpConfirmRequestVO(otpConfirmRequestVO);

        return this.accountService.findOrCreateAccount(findOrCreateAccountVO);
    }

    private SystemProperty getSystemProperty(SystemPropertyEnum systemProperty) {
        return this.systemPropertyService.getSystemProperty(systemProperty);
    }

    private Application validateApplication(int applicationId) {
        return this.applicationService.getApplication(applicationId);
    }

}


