package dk.unwire.ticketing.core.domain.application.service;

import dk.unwire.ticketing.core.domain.application.exception.ApplicationNotFoundException;
import dk.unwire.ticketing.core.domain.application.model.Application;
import dk.unwire.ticketing.core.domain.application.repository.ApplicationRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.mockito.BDDMockito.given;

@RunWith(MockitoJUnitRunner.class)
public class ApplicationServiceTest {
    private final static int INVALID_APPLICATION = 404;
    private final static int VALID_APPLICATION = 1;

    @InjectMocks
    private ApplicationService classUnderTest;
    @Mock
    private ApplicationRepository applicationRepository;

    @Test(expected = ApplicationNotFoundException.class)
    public void invalidApplicationIdShouldFail() {
        //given
        given(this.classUnderTest.getApplication(INVALID_APPLICATION)).willReturn(null);
        //when
        this.classUnderTest.getApplication(INVALID_APPLICATION);

    }

    public void validApplicationIdShouldReturnApplication() {
        //given
        given(this.classUnderTest.getApplication(VALID_APPLICATION)).willReturn(new Application());
        //when
        Application application = this.classUnderTest.getApplication(VALID_APPLICATION);
        //then
        Assert.assertNotNull(application);

    }
}
