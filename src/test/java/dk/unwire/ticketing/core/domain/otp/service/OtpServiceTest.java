package dk.unwire.ticketing.core.domain.otp.service;

import com.github.tomakehurst.wiremock.junit.WireMockClassRule;
import dk.unwire.ticketing.core.domain.application.enums.ApplicationPropertyKey;
import dk.unwire.ticketing.core.domain.application.exception.ApplicationPropertyException;
import dk.unwire.ticketing.core.domain.application.model.Application;
import dk.unwire.ticketing.core.domain.otp.OtpConstants;
import dk.unwire.ticketing.core.domain.otp.service.model.IvsRequestOtpVO;
import dk.unwire.ticketing.core.domain.otp.service.model.IvsResponseOtp;
import dk.unwire.ticketing.core.domain.systemproperty.model.SystemProperty;
import dk.unwire.ticketing.core.domain.systemproperty.model.SystemProperyFactory;
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
    private static final Integer IVS_CONTEXT_ID = 1;
    private static final String IVS_SENDER_NAME = "Unwire";
    private static final String IVS_MESSAGE_TEXT = "otp is:";
    private final long TEST_MSISDN = 12345678;
    private OtpService classUnderTest;
    private Application testApplication;
    private final SystemProperty testIvsBaseUrl = SystemProperyFactory.getTestSystemProperty();
    private IvsRequestOtpVO testIvsRequestOtpVO;

    @Before
    public void setUp() {
        this.classUnderTest = new OtpService();
        this.testApplication = mock(Application.class);

        createTestData();
        wireMockRule.givenThat(post(urlEqualTo(OtpConstants.IVS_REQUEST_RESOURCE))
                .willReturn(aResponse()
                        .withStatus(HttpStatus.OK.value())
                        .withHeader("Content-Type", "application/json")
                        .withBody("{ \"serialcode\" : \"bc4bbdd9-4e9e-4ebf-83bb-0d411a8ca33d\"}")));

        given(this.testApplication.getStringApplicationProperty(ApplicationPropertyKey.IVS_MESSAGE_TEXT)).willReturn(IVS_MESSAGE_TEXT);
        given(this.testApplication.getIntApplicationProperty(ApplicationPropertyKey.IVS_CONTEXT_ID)).willReturn(IVS_CONTEXT_ID);
        given(this.testApplication.getStringApplicationProperty(ApplicationPropertyKey.IVS_SENDER_NAME)).willReturn(IVS_SENDER_NAME);
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
        given(this.testApplication.getIntApplicationProperty(ApplicationPropertyKey.IVS_CONTEXT_ID)).willReturn(null);
        //when
        this.classUnderTest.requestOtp(this.testIvsRequestOtpVO);
    }

    @Test(expected = ApplicationPropertyException.class)
    public void missingApplicationPropertyIvsSenderNameShouldFail() {
        //given
        given(this.testApplication.getStringApplicationProperty(ApplicationPropertyKey.IVS_SENDER_NAME)).willReturn(null);
        //when
        this.classUnderTest.requestOtp(this.testIvsRequestOtpVO);
    }

    @Test
    public void missingIvsMessageTextShouldReturnOk() {
        //given
        given(this.testApplication.getStringApplicationProperty(ApplicationPropertyKey.IVS_MESSAGE_TEXT)).willReturn(null);

        // when
        ResponseEntity<IvsResponseOtp> ivsResponseOTPResponseEntity = this.classUnderTest.requestOtp(this.testIvsRequestOtpVO);
        Assert.assertEquals(HttpStatus.OK, ivsResponseOTPResponseEntity.getStatusCode());
    }

    @Test
    public void requestOtpAllOk() {
        // when
        ResponseEntity<IvsResponseOtp> ivsResponseOTPResponseEntity = this.classUnderTest.requestOtp(this.testIvsRequestOtpVO);
        Assert.assertEquals(HttpStatus.OK, ivsResponseOTPResponseEntity.getStatusCode());
    }
}


