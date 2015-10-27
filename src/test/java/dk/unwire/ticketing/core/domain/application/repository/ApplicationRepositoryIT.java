package dk.unwire.ticketing.core.domain.application.repository;

import dk.unwire.ticketing.core.TicketingCoreApplication;
import dk.unwire.ticketing.core.domain.application.enums.ApplicationPropertyKey;
import dk.unwire.ticketing.core.domain.application.model.Application;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.Assert.assertNotNull;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = TicketingCoreApplication.class)
@WebAppConfiguration
@Transactional
public class ApplicationRepositoryIT {

    @Autowired
    private ApplicationRepository applicationRepository;

    @Test
    public void getApplication() {
        Application savedApplication = this.applicationRepository.findOne(1);
        assertNotNull(savedApplication);
        assertNotNull(savedApplication.getStringProperty(ApplicationPropertyKey.IVS_SENDER_NAME.getKey()));
    }

}