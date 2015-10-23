package dk.unwire.ticketing.core.domain.account.service;

import dk.unwire.ticketing.core.TicketingCoreApplication;
import dk.unwire.ticketing.core.domain.account.model.AccountIdentifier;
import dk.unwire.ticketing.core.domain.account.model.FindOrCreateAccountVO;
import dk.unwire.ticketing.core.domain.application.model.Application;
import dk.unwire.ticketing.core.domain.otp.rest.model.confirm.OtpConfirmRequest;
import dk.unwire.ticketing.core.domain.otp.rest.model.confirm.OtpConfirmRequestVO;
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
    private Application testApplication;
    private FindOrCreateAccountVO findOrCreateAccountVO;
    @Autowired
    private AccountService classUnderTest;

    @Before
    public void setUp() {
        this.testApplication = new Application();
        OtpConfirmRequest otpConfirmRequest = new OtpConfirmRequest("4511111111", "1234");
        OtpConfirmRequestVO otpConfirmRequestVO = new OtpConfirmRequestVO(otpConfirmRequest, 1);
        this.findOrCreateAccountVO = FindOrCreateAccountVO.fromOtpConfirmRequestVO(otpConfirmRequestVO);

    }

    @Test
    public void insertAccountAndAccountIdentifier() {
        AccountIdentifier orCreateAccount = this.classUnderTest.findOrCreateAccount(this.findOrCreateAccountVO);
        assertNotNull(orCreateAccount);
    }

}
