package dk.unwire.ticketing.core.domain.ticket.repository;

import dk.unwire.ticketing.core.domain.ticket.model.Ticket;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TicketRepository extends CrudRepository<Ticket, Long> {
}
