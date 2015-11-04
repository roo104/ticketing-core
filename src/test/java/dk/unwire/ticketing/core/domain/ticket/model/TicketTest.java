package dk.unwire.ticketing.core.domain.ticket.model;

import dk.unwire.ticketing.core.TicketingCoreApplication;
import dk.unwire.ticketing.core.domain.account.model.Account;
import dk.unwire.ticketing.core.domain.account.repository.AccountRepository;
import dk.unwire.ticketing.core.domain.product.model.Product;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = TicketingCoreApplication.class)
@WebAppConfiguration
@Transactional
public class TicketTest {

    @Autowired
    private TicketFactory ticketFactory;
    @Autowired
    private AccountRepository accountRepository;

    private Ticket ticket;

    @Before
    public void setup() {
        Account account = this.accountRepository.findOne(1);
        this.ticket = this.ticketFactory.buildTicket(account, new Product());
    }

    @Test
    public void initTicketState() {

    }

}