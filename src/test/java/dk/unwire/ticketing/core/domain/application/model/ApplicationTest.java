package dk.unwire.ticketing.core.domain.application.model;

import dk.unwire.ticketing.core.domain.application.enums.ApplicationPropertyKey;
import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static junit.framework.Assert.assertEquals;

public class ApplicationTest {
    private Application classUnderTest;
    private Map<String, ApplicationProperty> applicationPropertyMap;
    private ApplicationProperty testApplicationProperty;
    public static final String IVS_CONTEXT_ID = "1";
    public static final int IVS_CONTEXT_ID_AS_INTEGER = 1;
    public static final String IVS_SENDER_NAME = "Unwire";
    public static final String DEFAULT_STRING_VALUE = "return me";
    public static final Integer DEFAULT_INT_VALUE = 10;

    @Before
    public void setUp() {
        this.classUnderTest = new Application();
        this.applicationPropertyMap = new HashMap<>();
        this.classUnderTest.setApplicationProperties(this.applicationPropertyMap);
    }

    @Test
    public void getStringApplicationProperty() {
        //given
        this.testApplicationProperty = new ApplicationProperty(ApplicationPropertyKey.IVS_SENDER_NAME.getPropertyKey(), IVS_SENDER_NAME);
        this.applicationPropertyMap.put(ApplicationPropertyKey.IVS_SENDER_NAME.getPropertyKey(), this.testApplicationProperty);
        //when
        String result = this.classUnderTest.getStringApplicationProperty(ApplicationPropertyKey.IVS_SENDER_NAME);
        //then
        assertEquals(IVS_SENDER_NAME, result);
    }

    @Test
    public void getStringApplicationPropertyThatIsEmptyShouldReturnNull() {
        //when
        String result = this.classUnderTest.getStringApplicationProperty(ApplicationPropertyKey.IVS_MESSAGE_TEXT);
        //then
        assertEquals(null, result);
    }

    @Test
    public void getStringApplicationPropertyThatIsEmptyWithDefaultValue() {
        //when
        String result = this.classUnderTest.getStringApplicationProperty(ApplicationPropertyKey.IVS_MESSAGE_TEXT, DEFAULT_STRING_VALUE);
        //then
        assertEquals(DEFAULT_STRING_VALUE, result);
    }

    @Test
    public void getIntApplicationProperty() {
        //given
        this.testApplicationProperty = new ApplicationProperty(ApplicationPropertyKey.IVS_CONTEXT_ID.getPropertyKey(), IVS_CONTEXT_ID);

        this.applicationPropertyMap.put(ApplicationPropertyKey.IVS_CONTEXT_ID.getPropertyKey(), this.testApplicationProperty);
        //when
        int result = this.classUnderTest.getIntApplicationProperty(ApplicationPropertyKey.IVS_CONTEXT_ID);
        //then
        assertEquals(IVS_CONTEXT_ID_AS_INTEGER, result);
    }

    @Test
    public void getIntApplicationPropertyThatIsEmptyShouldReturnNull() {
        //when
        Integer result = this.classUnderTest.getIntApplicationProperty(ApplicationPropertyKey.IVS_CONTEXT_ID);
        //then
        assertEquals(null, result);
    }

    @Test
    public void getIntApplicationPropertyThatIsEmptyWithDefaultValue() {
        //when
        Integer result = this.classUnderTest.getIntApplicationProperty(ApplicationPropertyKey.IVS_CONTEXT_ID, DEFAULT_INT_VALUE);
        //then
        assertEquals(DEFAULT_INT_VALUE, result);
    }

    @Test
    public void getIntApplicationPropertyNotAnInteger() {
        //given

        this.applicationPropertyMap.put(ApplicationPropertyKey.IVS_SENDER_NAME.getPropertyKey(), this.testApplicationProperty);
        //when
        Integer result = this.classUnderTest.getIntApplicationProperty(ApplicationPropertyKey.IVS_SENDER_NAME);
        //then
        assertEquals(null, result);
    }

    @Test
    public void getIntApplicationPropertyNotAnIntegerWithDefaultValue() {
        //given
        this.testApplicationProperty = new ApplicationProperty(ApplicationPropertyKey.IVS_SENDER_NAME.getPropertyKey(), IVS_SENDER_NAME);

        this.applicationPropertyMap.put(ApplicationPropertyKey.IVS_SENDER_NAME.getPropertyKey(), this.testApplicationProperty);
        //when
        Integer result = this.classUnderTest.getIntApplicationProperty(ApplicationPropertyKey.IVS_SENDER_NAME);
        //then
        assertEquals(null, result);
    }

}
