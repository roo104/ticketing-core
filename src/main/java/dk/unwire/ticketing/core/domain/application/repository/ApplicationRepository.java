package dk.unwire.ticketing.core.domain.application.repository;

import dk.unwire.ticketing.core.domain.application.model.Application;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ApplicationRepository extends CrudRepository<Application, Integer> {
}
