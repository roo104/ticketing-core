package dk.unwire.ticketing.core.domain.ticket.repository;

import dk.unwire.ticketing.core.TicketingCoreApplication;
import dk.unwire.ticketing.core.domain.ticket.model.Ticket;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = TicketingCoreApplication.class)
@WebAppConfiguration
@Transactional
public class TicketRepositoryIT {

    @Autowired
    private TicketRepository ticketRepository;

    @Test
    public void testFetchOne() {
        Ticket ticket = this.ticketRepository.findOne(1L);

        assertNotNull(ticket);
        assertEquals(1, ticket.getLogEntries().size());
    }
}