package dk.unwire.ticketing.core.domain.otp.service;

import com.github.tomakehurst.wiremock.junit.WireMockClassRule;
import dk.unwire.ticketing.core.domain.application.enums.ApplicationPropertyKey;
import dk.unwire.ticketing.core.domain.application.exception.ApplicationPropertyException;
import dk.unwire.ticketing.core.domain.application.model.Application;
import dk.unwire.ticketing.core.domain.otp.OtpConstants;
import dk.unwire.ticketing.core.domain.otp.enums.OtpResponseInfo;
import dk.unwire.ticketing.core.domain.otp.exception.IvsConfirmOtpErrorException;
import dk.unwire.ticketing.core.domain.otp.exception.IvsRequestOtpErrorException;
import dk.unwire.ticketing.core.domain.otp.service.model.confirm.IvsRequestConfirmOtpVO;
import dk.unwire.ticketing.core.domain.otp.service.model.register.IvsRequestOtpVO;
import dk.unwire.ticketing.core.domain.systemproperty.model.SystemProperty;
import dk.unwire.ticketing.core.domain.systemproperty.model.SystemProperyFactory;
import dk.unwire.ticketing.spring.rest.common.response.ResponseInfo;
import org.junit.Assert;
import org.junit.Before;
import org.junit.ClassRule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static dk.unwire.ticketing.core.domain.otp.OtpConstants.IVS_CONFIRM_RESOURCE;
import static dk.unwire.ticketing.core.domain.otp.OtpConstants.IVS_REQUEST_RESOURCE;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

@RunWith(MockitoJUnitRunner.class)
public class OtpServiceTest {
    @ClassRule
    public static WireMockClassRule wireMockRule = new WireMockClassRule(OtpConstants.WIREMOCK_PORT);
    private static final Integer IVS_CONTEXT_ID = 1;
    private static final String TEST_OTP = "1234";
    private static final String IVS_SENDER_NAME = "Unwire";
    private static final String IVS_MESSAGE_TEXT = "otp is:";
    private final String TEST_MSISDN = "12345678";
    private OtpService classUnderTest;
    private Application testApplication;
    private final SystemProperty testIvsBaseUrl = SystemProperyFactory.getTestSystemProperty();
    private IvsRequestOtpVO testIvsRequestOtpVO;
    private IvsRequestConfirmOtpVO ivsRequestConfirmOtpVO;

    @Before
    public void setUp() {
        this.classUnderTest = new OtpService();
        this.testApplication = mock(Application.class);

        createTestData();
        setupRestMocks();

        given(this.testApplication.getStringApplicationProperty(ApplicationPropertyKey.IVS_MESSAGE_TEXT)).willReturn(IVS_MESSAGE_TEXT);
        given(this.testApplication.getIntApplicationProperty(ApplicationPropertyKey.IVS_CONTEXT_ID)).willReturn(IVS_CONTEXT_ID);
        given(this.testApplication.getStringApplicationProperty(ApplicationPropertyKey.IVS_SENDER_NAME)).willReturn(IVS_SENDER_NAME);
    }

    private void setupRestMocks() {
        wireMockRule.givenThat(post(urlEqualTo(IVS_REQUEST_RESOURCE))
                .willReturn(aResponse()
                        .withStatus(HttpStatus.OK.value())
                        .withBody("bc4bbdd9-4e9e-4ebf-83bb-0d411a8ca33d")));

        wireMockRule.givenThat(post(urlEqualTo(IVS_CONFIRM_RESOURCE))
                .willReturn(aResponse()
                        .withStatus(HttpStatus.OK.value())
                        .withBody("true")));
    }

    private void createTestData() {

        this.testIvsRequestOtpVO = IvsRequestOtpVO.builder()
                .systemProperty(this.testIvsBaseUrl)
                .msisdn(this.TEST_MSISDN)
                .application(this.testApplication)
                .build();
        this.ivsRequestConfirmOtpVO = IvsRequestConfirmOtpVO.builder()
                .otp(TEST_OTP)
                .application(this.testApplication)
                .msisdn(this.TEST_MSISDN)
                .systemProperty(this.testIvsBaseUrl)
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
        this.classUnderTest.requestOtp(this.testIvsRequestOtpVO);
        //then
        verify(postRequestedFor(urlEqualTo("/context/1/validation/identity/12345678"))
                .withHeader("Content-Type", equalTo("application/json")));
    }


    @Test
    public void requestOtpAllOk() {
        // when
        this.classUnderTest.requestOtp(this.testIvsRequestOtpVO);
        verify(postRequestedFor(urlEqualTo("/context/1/validation/identity/12345678"))
                .withHeader("Content-Type", equalTo("application/json")));
    }

    @Test(expected = ApplicationPropertyException.class)
    public void confirmMissingApplicationPropertyIvsContextIdShouldFail() {
        //given
        given(this.testApplication.getIntApplicationProperty(ApplicationPropertyKey.IVS_CONTEXT_ID)).willReturn(null);
        //when
        this.classUnderTest.confirmOtp(this.ivsRequestConfirmOtpVO);
    }

    @Test
    public void requestReturnsBadRequestShouldFail() {
        OtpResponseInfo result = null;
        //given
        wireMockRule.givenThat(post(urlEqualTo(IVS_REQUEST_RESOURCE))
                .willReturn(aResponse()
                        .withStatus(HttpStatus.BAD_REQUEST.value())));
        //when
        try {
            this.classUnderTest.requestOtp(this.testIvsRequestOtpVO);

        } catch (IvsRequestOtpErrorException e) {
            result = e.getOtpResponseInfo();
        }
        //then
        Assert.assertEquals(OtpResponseInfo.OTP_REQUEST_DEFAULT_ERROR, result);

    }

    @Test
    public void requestUserBlocked() {
        OtpResponseInfo result = null;
        //given
        wireMockRule.givenThat(post(urlEqualTo(IVS_REQUEST_RESOURCE))
                .willReturn(aResponse()
                        .withStatus(HttpStatus.FORBIDDEN.value())));
        //when
        try {
            this.classUnderTest.requestOtp(this.testIvsRequestOtpVO);

        } catch (IvsRequestOtpErrorException e) {
            result = e.getOtpResponseInfo();
        }
        //then
        Assert.assertEquals(OtpResponseInfo.OTP_MSISDN_BLOCKED, result);

    }

    @Test
    public void requestIvsInternalServerErrorShouldFail() {
        OtpResponseInfo result = null;
        //given
        wireMockRule.givenThat(post(urlEqualTo(IVS_REQUEST_RESOURCE))
                .willReturn(aResponse()
                        .withStatus(HttpStatus.INTERNAL_SERVER_ERROR.value())));
        //when
        try {
            this.classUnderTest.requestOtp(this.testIvsRequestOtpVO);

        } catch (IvsRequestOtpErrorException e) {
            result = e.getOtpResponseInfo();
        }
        //then
        Assert.assertEquals(OtpResponseInfo.OTP_REQUEST_SMS_NOT_SENT, result);
    }


    @Test
    public void confirmIvsAllOk() {
        //when
        this.classUnderTest.confirmOtp(this.ivsRequestConfirmOtpVO);
        //then
        verify(postRequestedFor(urlEqualTo("/context/1/confirmation/"))
                .withHeader("Content-Type", equalTo("application/json"))
                .withRequestBody(equalToJson(OtpConstants.EXPECTED_CONFIRMATION_REQUEST)));
    }

    @Test
    public void confirmIvsWrongOtpShouldFail() {
        ResponseInfo result = null;
        //given
        wireMockRule.givenThat(post(urlEqualTo(IVS_CONFIRM_RESOURCE))
                .willReturn(aResponse()
                        .withStatus(HttpStatus.NOT_FOUND.value())));
        //when
        try {
            this.classUnderTest.confirmOtp(this.ivsRequestConfirmOtpVO);
        } catch (IvsConfirmOtpErrorException e) {
            result = e.getOtpResponseInfo();
        }
        //then
        Assert.assertEquals(OtpResponseInfo.OTP_SIGNUP_FAILED, result);
    }

    @Test
    public void confirmIvsInternalServerErrorShouldFail() {
        ResponseInfo result = null;
        //given
        wireMockRule.givenThat(post(urlEqualTo(IVS_CONFIRM_RESOURCE))
                .willReturn(aResponse()
                        .withStatus(HttpStatus.INTERNAL_SERVER_ERROR.value())));
        //when
        try {
            this.classUnderTest.confirmOtp(this.ivsRequestConfirmOtpVO);
        } catch (IvsConfirmOtpErrorException e) {
            result = e.getOtpResponseInfo();
        }
        //then
        Assert.assertEquals(OtpResponseInfo.OTP_VALIDATION_FAILED, result);

    }

    @Test
    public void confirmIvsNotFoundErrorShouldFail() {
        ResponseInfo result = null;
        //given
        wireMockRule.givenThat(post(urlEqualTo(IVS_CONFIRM_RESOURCE))
                .willReturn(aResponse()
                        .withStatus(HttpStatus.NOT_FOUND.value())));
        //when
        try {
            this.classUnderTest.confirmOtp(this.ivsRequestConfirmOtpVO);
        } catch (IvsConfirmOtpErrorException e) {
            result = e.getOtpResponseInfo();
        }
        //then
        Assert.assertEquals(OtpResponseInfo.OTP_SIGNUP_FAILED, result);
    }
}


