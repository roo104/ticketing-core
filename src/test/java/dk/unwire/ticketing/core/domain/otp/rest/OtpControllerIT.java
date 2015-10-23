package dk.unwire.ticketing.core.domain.otp.rest;

import com.github.tomakehurst.wiremock.client.WireMock;
import com.github.tomakehurst.wiremock.junit.WireMockClassRule;
import dk.unwire.ticketing.core.TicketingCoreApplication;
import dk.unwire.ticketing.core.domain.otp.OtpConstants;
import dk.unwire.ticketing.core.domain.systemproperty.model.SystemProperyFactory;
import dk.unwire.ticketing.core.domain.systemproperty.repository.SystemPropertyRepository;
import dk.unwire.ticketing.spring.rest.config.filter.requestid.RequestIdFilter;
import org.junit.Before;
import org.junit.ClassRule;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;

import javax.transaction.Transactional;

import static com.github.tomakehurst.wiremock.client.WireMock.aResponse;
import static com.github.tomakehurst.wiremock.client.WireMock.urlEqualTo;
import static dk.unwire.ticketing.core.domain.otp.OtpConstants.IVS_CONFIRM_RESOURCE;
import static dk.unwire.ticketing.core.domain.otp.OtpConstants.IVS_REQUEST_RESOURCE;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = TicketingCoreApplication.class)
@TransactionConfiguration(defaultRollback = true)
@WebAppConfiguration
@Transactional
public class OtpControllerIT {
    @ClassRule
    public static WireMockClassRule wireMockRule = new WireMockClassRule(OtpConstants.WIREMOCK_PORT);
    private MockMvc mockMvc;
    @Autowired
    private WebApplicationContext webApplicationContext;
    @Autowired
    private RequestIdFilter requestIdFilter;
    private HttpHeaders httpHeaders;
    private OtpController classUnderTest;
    @Autowired
    private SystemPropertyRepository systemPropertyRepository;

    @Before
    public void setUp() throws Exception {
        this.mockMvc = webAppContextSetup(this.webApplicationContext).addFilter(this.requestIdFilter).build();
        this.httpHeaders = new HttpHeaders();
        this.httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        addTestData();
        mockEndpoints();
    }

    private void mockEndpoints() {
        wireMockRule.givenThat(WireMock.post(urlEqualTo(IVS_REQUEST_RESOURCE))
                .willReturn(aResponse()
                        .withStatus(HttpStatus.OK.value())
                        .withBody("bc4bbdd9-4e9e-4ebf-83bb-0d411a8ca33d")));

        wireMockRule.givenThat(WireMock.post(urlEqualTo(IVS_CONFIRM_RESOURCE))
                .willReturn(aResponse()
                        .withStatus(HttpStatus.OK.value())
                        .withBody("true")));
    }

    private void addTestData() {
        this.systemPropertyRepository.save(SystemProperyFactory.getTestSystemProperty());
    }

    /**
     * Can be used to test integration against the external IVS. Should always be disabled
     * and only run manually as IVS points to Production Gateway
     *
     * @throws Exception
     */
    @Ignore
    @Test
    public void requestOtpAllOk() throws Exception {
        this.mockMvc.perform(post("/v1/1/otp/")
                .content("{\"msisdn\": 4520925064}")
                .headers(this.httpHeaders))
                .andExpect(status().isOk())
                .andDo(print());
    }
}
