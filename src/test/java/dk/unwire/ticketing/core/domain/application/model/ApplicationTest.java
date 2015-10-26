package dk.unwire.ticketing.core.domain.application.model;

import dk.unwire.ticketing.core.domain.application.enums.ApplicationPropertyKey;
import org.junit.Before;
import org.junit.Test;

import static junit.framework.Assert.assertEquals;

public class ApplicationTest {
    private Application classUnderTest;
    private ApplicationProperty testApplicationProperty;
    public static final String IVS_CONTEXT_ID = "1";
    public static final int IVS_CONTEXT_ID_AS_INTEGER = 1;
    public static final String IVS_SENDER_NAME = "Unwire";
    public static final String DEFAULT_STRING_VALUE = "return me";
    public static final Integer DEFAULT_INT_VALUE = 10;

    @Before
    public void setUp() {
        this.classUnderTest = new Application();
        this.testApplicationProperty = new ApplicationProperty(ApplicationPropertyKey.IVS_SENDER_NAME.getPropertyKey(), IVS_SENDER_NAME);
        this.classUnderTest.addProperty()
        this.applicationPropertyMap.put(ApplicationPropertyKey.IVS_SENDER_NAME.getPropertyKey(), this.testApplicationProperty);
    }

    @Test
    public void getStringProperty() {
        //given

        //when
        String result = this.classUnderTest.getStringProperty(ApplicationPropertyKey.IVS_SENDER_NAME.getPropertyKey());
        //then
        assertEquals(IVS_SENDER_NAME, result);
    }

    @Test
    public void getStringPropertyThatIsEmptyShouldReturnNull() {
        //when
        String result = this.classUnderTest.getStringProperty(ApplicationPropertyKey.IVS_MESSAGE_TEXT.getPropertyKey());
        //then
        assertEquals(null, result);
    }

    @Test
    public void getStringPropertyThatIsEmptyWithDefaultValue() {
        //when
        String result = this.classUnderTest.getStringProperty(ApplicationPropertyKey.IVS_MESSAGE_TEXT.getPropertyKey(), DEFAULT_STRING_VALUE);
        //then
        assertEquals(DEFAULT_STRING_VALUE, result);
    }

    @Test
    public void getIntProperty() {
        //given
        this.testApplicationProperty = new ApplicationProperty(ApplicationPropertyKey.IVS_CONTEXT_ID.getPropertyKey(), IVS_CONTEXT_ID);

        this.applicationPropertyMap.put(ApplicationPropertyKey.IVS_CONTEXT_ID.getPropertyKey(), this.testApplicationProperty);
        //when
        int result = this.classUnderTest.getIntProperty(ApplicationPropertyKey.IVS_CONTEXT_ID.getPropertyKey());
        //then
        assertEquals(IVS_CONTEXT_ID_AS_INTEGER, result);
    }

    @Test
    public void getIntPropertyThatIsEmptyShouldReturnNull() {
        //when
        Integer result = this.classUnderTest.getIntProperty(ApplicationPropertyKey.IVS_CONTEXT_ID.getPropertyKey());
        //then
        assertEquals(null, result);
    }

    @Test
    public void getIntPropertyThatIsEmptyWithDefaultValue() {
        //when
        Integer result = this.classUnderTest.getIntProperty(ApplicationPropertyKey.IVS_CONTEXT_ID.getPropertyKey(), DEFAULT_INT_VALUE);
        //then
        assertEquals(DEFAULT_INT_VALUE, result);
    }

    @Test
    public void getIntPropertyNotAnInteger() {
        //given

        this.applicationPropertyMap.put(ApplicationPropertyKey.IVS_SENDER_NAME.getPropertyKey(), this.testApplicationProperty);
        //when
        Integer result = this.classUnderTest.getIntProperty(ApplicationPropertyKey.IVS_SENDER_NAME.getPropertyKey());
        //then
        assertEquals(null, result);
    }

    @Test
    public void getIntPropertyNotAnIntegerWithDefaultValue() {
        //given
        this.testApplicationProperty = new ApplicationProperty(ApplicationPropertyKey.IVS_SENDER_NAME.getPropertyKey(), IVS_SENDER_NAME);

        this.applicationPropertyMap.put(ApplicationPropertyKey.IVS_SENDER_NAME.getPropertyKey(), this.testApplicationProperty);
        //when
        Integer result = this.classUnderTest.getIntProperty(ApplicationPropertyKey.IVS_SENDER_NAME.getPropertyKey());
        //then
        assertEquals(null, result);
    }

}
