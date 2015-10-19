package dk.unwire.ticketing.core.domain.systemproperty.service;

import dk.unwire.ticketing.core.domain.systemproperty.enums.SystemPropertyEnum;
import dk.unwire.ticketing.core.domain.systemproperty.model.SystemProperty;
import dk.unwire.ticketing.core.domain.systemproperty.repository.SystemPropertyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SystemPropertyService {
    @Autowired
    private SystemPropertyRepository systemPropertyRepository;

    public SystemProperty getSystemProperty(SystemPropertyEnum systemPropertyEnum) {
        SystemProperty systemProperty = this.systemPropertyRepository.getPropertyByName(systemPropertyEnum.getProperty());

        return systemProperty;
    }
}
