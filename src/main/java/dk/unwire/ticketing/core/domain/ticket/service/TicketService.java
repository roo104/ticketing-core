package dk.unwire.ticketing.core.domain.ticket.service;

import dk.unwire.ticketing.core.domain.ticket.model.factory.CreateTicketVO;
import dk.unwire.ticketing.core.domain.ticket.model.factory.TicketFactory;
import dk.unwire.ticketing.core.domain.ticket.model.vo.TicketOrder;
import dk.unwire.ticketing.core.domain.ticket.repository.TicketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TicketService {

    @Autowired
    private TicketFactory ticketFactory;
    @Autowired
    private TicketRepository ticketRepository;

    public void createTickets(CreateTicketVO createTicketVO) {
        TicketOrder ticketOrder = this.ticketFactory.createTickets(createTicketVO);

        this.ticketRepository.save(ticketOrder.getAllTickets());
    }
}
