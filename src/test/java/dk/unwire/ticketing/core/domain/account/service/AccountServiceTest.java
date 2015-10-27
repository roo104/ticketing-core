package dk.unwire.ticketing.core.domain.account.service;

import dk.unwire.ticketing.core.domain.account.enums.IdentifierType;
import dk.unwire.ticketing.core.domain.account.model.Account;
import dk.unwire.ticketing.core.domain.account.model.AccountIdentifier;
import dk.unwire.ticketing.core.domain.account.model.FindOrCreateAccountVO;
import dk.unwire.ticketing.core.domain.account.repository.AccountIdentifierRepository;
import dk.unwire.ticketing.core.domain.account.repository.AccountRepository;
import dk.unwire.ticketing.core.domain.otp.model.OtpConfirmRequestVO;
import dk.unwire.ticketing.core.domain.otp.rest.model.confirm.OtpConfirmRequest;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static junit.framework.Assert.assertNotNull;
import static org.mockito.BDDMockito.given;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class AccountServiceTest {
    private static final String MSISDN = "4511111111";
    private static final String OTP = "1234";
    private static final int APPLICATION_ID = 1;
    private FindOrCreateAccountVO findOrCreateAccountVO;
    @InjectMocks
    private AccountService classUnderTest;
    @Mock
    private AccountRepository accountRepository;
    @Mock
    private AccountIdentifierRepository accountIdentifierRepository;
    private AccountIdentifier accountIdentifier;

    @Before
    public void setUp() {
        OtpConfirmRequest otpConfirmRequest = new OtpConfirmRequest(MSISDN, OTP);
        OtpConfirmRequestVO otpConfirmRequestVO = otpConfirmRequest.generateOtpConfirmRequestVO(APPLICATION_ID);
        this.findOrCreateAccountVO = FindOrCreateAccountVO.fromOtpConfirmRequestVO(otpConfirmRequestVO);
        this.accountIdentifier = AccountIdentifier.builder()
                .identifier(MSISDN)
                .applicationId(APPLICATION_ID)
                .build();
    }

    @Test
    public void accountIdentifierNotFoundCreateAccountAndAccountIdentifier() {
        //given
        given(this.accountIdentifierRepository.findByIdentifierAndIdentifierTypeAndApplicationId(anyString(), any(IdentifierType.class), anyInt())).willReturn(null);
        //when
        AccountIdentifier accountIdentifier = this.classUnderTest.findOrCreateAccount(this.findOrCreateAccountVO);
        //then
        assertNotNull(accountIdentifier);
        verify(this.accountRepository, times(1)).save(any(Account.class));
    }

    @Test
    public void findAccountFromIdentifierShouldReturnAccountIdentifier() {
        //given
        given(this.accountIdentifierRepository.findByIdentifierAndIdentifierTypeAndApplicationId(anyString(), any(IdentifierType.class), anyInt())).willReturn(this.accountIdentifier);
        //when
        AccountIdentifier accountIdentifier = this.classUnderTest.findOrCreateAccount(this.findOrCreateAccountVO);
        //then
        assertNotNull(accountIdentifier);
        verify(this.accountRepository, never()).save(any(Account.class));
    }

}
