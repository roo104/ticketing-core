package domain.otp.service;

import com.github.tomakehurst.wiremock.junit.WireMockClassRule;
import dk.unwire.ticketing.core.domain.application.enums.ApplicationPropertyKey;
import dk.unwire.ticketing.core.domain.application.exception.ApplicationPropertyException;
import dk.unwire.ticketing.core.domain.application.model.Application;
import dk.unwire.ticketing.core.domain.otp.service.OtpService;
import dk.unwire.ticketing.core.domain.otp.service.model.IvsResponseOtpRenamew;
import dk.unwire.ticketing.core.domain.otp.service.model.IvsRequestOtpVO;
import dk.unwire.ticketing.core.domain.systemproperty.model.SystemProperty;
import domain.otp.OtpConstants;
import org.junit.Assert;
import org.junit.Before;
import org.junit.ClassRule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

@RunWith(MockitoJUnitRunner.class)
public class OtpServiceTest {

    @ClassRule
    public static WireMockClassRule wireMockRule = new WireMockClassRule(OtpConstants.WIREMOCK_PORT);
    public static final Integer IVS_CONTEXT_ID = 1;
    public static final String IVS_SENDER_NAME = "Unwire";
    public static final String IVS_MESSAGE_TEXT = "otp is:";
    private OtpService classUnderTest;
    private Application testApplication;
    private SystemProperty testIvsBaseUrl;
    private IvsRequestOtpVO testIvsRequestOtpVO;
    private final long TEST_MSISDN = 12345678;

    @Before
    public void setUp() {
        this.classUnderTest = new OtpService();
        this.testApplication = mock(Application.class);
        this.testIvsBaseUrl = mock(SystemProperty.class);

        createTestData();
        given(this.testIvsRequestOtpVO.getSystemProperty().getValue()).willReturn(OtpConstants.IVS_BASE_URL);

        wireMockRule.givenThat(post(urlEqualTo(OtpConstants.IVS_REQUEST_RESOURCE))
                .willReturn(aResponse()
                        .withStatus(HttpStatus.OK.value())
                        .withHeader("Content-Type", "application/json")
                        .withBody("{ \"serialcode\" : \"bc4bbdd9-4e9e-4ebf-83bb-0d411a8ca33d\"}")));
    }

    private void createTestData() {

        this.testIvsRequestOtpVO = IvsRequestOtpVO.newBuilder()
                .withSystemProperty(this.testIvsBaseUrl)
                .withMsisdn(this.TEST_MSISDN)
                .withApplication(this.testApplication)
                .build();

    }


    @Test(expected = ApplicationPropertyException.class)
    public void missingApplicationPropertyIvsContextIdShouldFail() {
        //given
        given(this.testApplication.getStringApplicationProperty(ApplicationPropertyKey.IVS_SENDER_NAME)).willReturn(IVS_SENDER_NAME);
        given(this.testApplication.getStringApplicationProperty(ApplicationPropertyKey.IVS_MESSAGE_TEXT)).willReturn(IVS_MESSAGE_TEXT);
        given(this.testApplication.getIntApplicationProperty(ApplicationPropertyKey.IVS_CONTEXT_ID)).willReturn(null);
        //when
        this.classUnderTest.requestOtp(this.testIvsRequestOtpVO);
    }

    @Test(expected = ApplicationPropertyException.class)
    public void missingApplicationPropertyIvsSenderNameShouldFail() {
        //given
        given(this.testApplication.getIntApplicationProperty(ApplicationPropertyKey.IVS_CONTEXT_ID)).willReturn(IVS_CONTEXT_ID);
        given(this.testApplication.getStringApplicationProperty(ApplicationPropertyKey.IVS_MESSAGE_TEXT)).willReturn(IVS_MESSAGE_TEXT);
        given(this.testApplication.getStringApplicationProperty(ApplicationPropertyKey.IVS_SENDER_NAME)).willReturn(null);
        //when
        this.classUnderTest.requestOtp(this.testIvsRequestOtpVO);
    }

    @Test
    public void missingIvsMessageTextShouldReturnOk(){
        //given
        given(this.testApplication.getStringApplicationProperty(ApplicationPropertyKey.IVS_MESSAGE_TEXT)).willReturn(null);
        given(this.testApplication.getIntApplicationProperty(ApplicationPropertyKey.IVS_CONTEXT_ID)).willReturn(IVS_CONTEXT_ID);
        given(this.testApplication.getStringApplicationProperty(ApplicationPropertyKey.IVS_SENDER_NAME)).willReturn(IVS_SENDER_NAME);
        // when
        ResponseEntity<IvsResponseOtpRenamew> ivsResponseOTPResponseEntity = this.classUnderTest.requestOtp(this.testIvsRequestOtpVO);
        Assert.assertEquals(HttpStatus.OK, ivsResponseOTPResponseEntity.getStatusCode());

    }


    @Test
    public void requestOtpAllOk() {
        //given
        given(this.testApplication.getStringApplicationProperty(ApplicationPropertyKey.IVS_MESSAGE_TEXT)).willReturn(IVS_MESSAGE_TEXT);
        given(this.testApplication.getIntApplicationProperty(ApplicationPropertyKey.IVS_CONTEXT_ID)).willReturn(IVS_CONTEXT_ID);
        given(this.testApplication.getStringApplicationProperty(ApplicationPropertyKey.IVS_SENDER_NAME)).willReturn(IVS_SENDER_NAME);
        // when
        ResponseEntity<IvsResponseOtpRenamew> ivsResponseOTPResponseEntity = this.classUnderTest.requestOtp(this.testIvsRequestOtpVO);
        Assert.assertEquals(HttpStatus.OK, ivsResponseOTPResponseEntity.getStatusCode());

    }
}


