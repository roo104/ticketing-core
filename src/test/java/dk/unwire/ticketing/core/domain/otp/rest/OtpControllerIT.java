package dk.unwire.ticketing.core.domain.otp.rest;

import com.github.tomakehurst.wiremock.client.WireMock;
import com.github.tomakehurst.wiremock.junit.WireMockClassRule;
import dk.unwire.ticketing.core.TicketingCoreApplication;
import dk.unwire.ticketing.core.domain.otp.OtpConstants;
import dk.unwire.ticketing.spring.rest.config.filter.requestid.RequestIdFilter;
import org.junit.*;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.restdocs.RestDocumentation;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;

import javax.transaction.Transactional;

import static com.github.tomakehurst.wiremock.client.WireMock.aResponse;
import static com.github.tomakehurst.wiremock.client.WireMock.urlEqualTo;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.documentationConfiguration;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = TicketingCoreApplication.class)
@Rollback
@WebAppConfiguration
@Transactional
public class OtpControllerIT {

    @Rule
    public RestDocumentation restDocumentation = new RestDocumentation("target/generated-snippets");

    @ClassRule
    public static WireMockClassRule wireMockRule = new WireMockClassRule(OtpConstants.WIREMOCK_PORT);

    private MockMvc mockMvc;
    @Autowired
    private WebApplicationContext webApplicationContext;
    @Autowired
    private RequestIdFilter requestIdFilter;

    private HttpHeaders httpHeaders;

    @Before
    public void setUp() throws Exception {
        this.mockMvc = webAppContextSetup(this.webApplicationContext)
                .apply(documentationConfiguration(this.restDocumentation))
                .addFilter(this.requestIdFilter)
                .build();
        this.httpHeaders = new HttpHeaders();
        this.httpHeaders.setContentType(MediaType.APPLICATION_JSON);

        wireMockRule.givenThat(WireMock.post(urlEqualTo("/ivs"))
                .willReturn(aResponse()
                        .withStatus(HttpStatus.OK.value())));
    }

    /**
     * Can be used to test integration against the external IVS. Should always be disabled
     * and only run manually as IVS points to Production Gateway
     *
     * @throws Exception
     */
    @Test
    public void requestOtpAllOk() throws Exception {
        this.mockMvc.perform(post("/v1/1/otp/")
                .content("{\"msisdn\": 4551967719}")
                .headers(this.httpHeaders))
                .andExpect(status().isOk())
                .andDo(print());
    }

	@Ignore
	@Test
	public void thatOTPConfirmationWorks() throws Exception {

		this.mockMvc.perform(put("/v1/1/otp/confirm/")
				.content("{\"msisdn\":\"4571378012\",\"otp\": \"8044\"}")
				.headers(this.httpHeaders))
				.andExpect(status().isOk())
				.andDo(print());
	}
}
