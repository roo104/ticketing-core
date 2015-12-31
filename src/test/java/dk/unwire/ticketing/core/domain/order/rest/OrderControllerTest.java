package dk.unwire.ticketing.core.domain.order.rest;

import dk.unwire.ticketing.core.TicketingCoreApplication;
import dk.unwire.ticketing.spring.rest.common.response.GenericResponseInfo;
import dk.unwire.ticketing.spring.rest.config.filter.requestid.RequestIdFilter;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.web.context.WebApplicationContext;

import javax.transaction.Transactional;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = TicketingCoreApplication.class)
@Rollback
@WebAppConfiguration
@Transactional
public class OrderControllerTest {

    private MockMvc mockMvc;
    @Autowired
    private WebApplicationContext webApplicationContext;
    @Autowired
    private RequestIdFilter requestIdFilter;

    private HttpHeaders httpHeaders;

    @Before
    public void setUp() throws Exception {
        this.httpHeaders = new HttpHeaders();
        this.httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        this.mockMvc = webAppContextSetup(this.webApplicationContext).addFilter(this.requestIdFilter).build();
    }

    @Test
    public void testCreateOrder() throws Exception {
        // when
        ResultActions resultActions = this.mockMvc.perform(post("/v1/1/order")
                .content("{\"msisdn\": 4571378012}")
                .headers(this.httpHeaders))
                .andDo(print());

        // then
        resultActions.andExpect(status().isCreated())
                .andExpect(jsonPath("$.status.code", is(GenericResponseInfo.OK.getStatus())))
                .andExpect(jsonPath("$.status.requestId").exists());
    }

    @Test
    public void testUpdateOrder() throws Exception {
        // when
        ResultActions resultActions = this.mockMvc.perform(put("/v1/1/order")
                .content("{\"msisdn\": 4571378012}")
                .headers(this.httpHeaders))
                .andDo(print());

        // then
        resultActions.andExpect(status().isOk())
                .andExpect(jsonPath("$.status.code", is(GenericResponseInfo.OK.getStatus())))
                .andExpect(jsonPath("$.status.requestId").exists());
    }
}