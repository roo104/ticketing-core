package dk.unwire.ticketing.core.domain.otp.application;

import dk.unwire.ticketing.core.domain.application.model.Application;
import dk.unwire.ticketing.core.domain.application.service.ApplicationService;
import dk.unwire.ticketing.core.domain.otp.rest.model.OtpRequestVO;
import dk.unwire.ticketing.core.domain.otp.service.OtpService;
import dk.unwire.ticketing.core.domain.otp.service.model.IvsRequestOtpVO;
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
    SystemPropertyService systemPropertyService;

    public void requestOtp(OtpRequestVO requestVO) {
        Application application = this.applicationService.getApplication(requestVO.getApplicationId());
        SystemProperty baseUrlProperty = this.systemPropertyService.getSystemProperty(SystemPropertyEnum.IVS_BASEURL);

        IvsRequestOtpVO ivsRequestOtpVO = IvsRequestOtpVO.newBuilder()
                .withApplication(application)
                .withMsisdn(requestVO.getMsisdn())
                .withSystemProperty(baseUrlProperty)
                .build();

     this.otpService.requestOtp(ivsRequestOtpVO);
    }

}


