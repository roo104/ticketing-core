package dk.unwire.ticketing.core.domain.account.service;

import dk.unwire.ticketing.core.TicketingCoreApplication;
import dk.unwire.ticketing.core.domain.account.model.AccountIdentifier;
import dk.unwire.ticketing.core.domain.account.model.FindOrCreateAccountVO;
import dk.unwire.ticketing.core.domain.otp.model.OtpConfirmRequestVO;
import dk.unwire.ticketing.core.domain.otp.rest.model.confirm.OtpConfirmRequest;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.test.context.web.WebAppConfiguration;

import javax.transaction.Transactional;

import static junit.framework.Assert.assertNotNull;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = TicketingCoreApplication.class)
@TransactionConfiguration(defaultRollback = true)
@WebAppConfiguration
@Transactional
public class AccountServiceTest {
    public static final String MSISDN = "4511111111";
    public static final String OTP = "1234";
    public static final int APPLICATION_ID = 1;
    private FindOrCreateAccountVO findOrCreateAccountVO;
    @Autowired
    private AccountService classUnderTest;

    @Before
    public void setUp() {
        OtpConfirmRequest otpConfirmRequest = new OtpConfirmRequest(MSISDN, OTP);

        OtpConfirmRequestVO otpConfirmRequestVO = otpConfirmRequest.generateOtpConfirmRequestVO(APPLICATION_ID);
        this.findOrCreateAccountVO = FindOrCreateAccountVO.fromOtpConfirmRequestVO(otpConfirmRequestVO);
    }

    @Test
    public void insertAccountAndAccountIdentifier() {
        AccountIdentifier orCreateAccount = this.classUnderTest.findOrCreateAccount(this.findOrCreateAccountVO);
        assertNotNull(orCreateAccount);
    }

}
