package dk.unwire.ticketing.core.domain.systemproperty.repository;

import dk.unwire.ticketing.core.domain.systemproperty.model.SystemProperty;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SystemPropertyRepository extends CrudRepository<SystemProperty, Integer> {
    SystemProperty getPropertyByName(String name);
}
