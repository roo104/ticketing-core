package dk.unwire.ticketing.core.domain.application.service;

import dk.unwire.ticketing.core.domain.application.exception.ApplicationNotFoundException;
import dk.unwire.ticketing.core.domain.application.model.Application;
import dk.unwire.ticketing.core.domain.application.repository.ApplicationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ApplicationService {
    @Autowired
    private ApplicationRepository applicationRepository;

    public Application getApplication(int applicationId) {
        Application application = this.applicationRepository.findOne(applicationId);

        if (application == null) {
            throw new ApplicationNotFoundException("Invalid Application ID: [" + applicationId + "]");
        }
        return application;

    }
}
