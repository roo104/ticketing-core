package dk.unwire.ticketing.core.domain.application.model;

import dk.unwire.ticketing.core.domain.application.enums.ApplicationPropertyKey;
import org.junit.Before;
import org.junit.Test;

import static junit.framework.Assert.assertEquals;

public class ApplicationTest {

    private Application classUnderTest;

    public static final String IVS_CONTEXT_ID = "1";
    public static final int IVS_CONTEXT_ID_AS_INTEGER = 1;
    public static final String IVS_SENDER_NAME = "Unwire";
    public static final String DEFAULT_STRING_VALUE = "return me";
    public static final Integer DEFAULT_INT_VALUE = 10;

    @Before
    public void setUp() {
        this.classUnderTest = new Application();
        this.classUnderTest.addProperty(new ApplicationProperty(ApplicationPropertyKey.IVS_SENDER_NAME.getKey(), IVS_SENDER_NAME));
        this.classUnderTest.addProperty(new ApplicationProperty(ApplicationPropertyKey.IVS_CONTEXT_ID.getKey(), IVS_CONTEXT_ID));
    }

    @Test
    public void getStringProperty() {
        //when
        String result = this.classUnderTest.getStringProperty(ApplicationPropertyKey.IVS_SENDER_NAME.getKey());
        //then
        assertEquals(IVS_SENDER_NAME, result);
    }

    @Test
    public void getStringPropertyThatIsEmptyShouldReturnNull() {
        //when
        String result = this.classUnderTest.getStringProperty(ApplicationPropertyKey.IVS_MESSAGE_TEXT.getKey());
        //then
        assertEquals(null, result);
    }

    @Test
    public void getStringPropertyThatIsEmptyWithDefaultValue() {
        //when
        String result = this.classUnderTest.getStringProperty(ApplicationPropertyKey.IVS_MESSAGE_TEXT.getKey(), DEFAULT_STRING_VALUE);
        //then
        assertEquals(DEFAULT_STRING_VALUE, result);
    }

    @Test
    public void getIntProperty() {
        //when
        int result = this.classUnderTest.getIntProperty(ApplicationPropertyKey.IVS_CONTEXT_ID.getKey());
        //then
        assertEquals(IVS_CONTEXT_ID_AS_INTEGER, result);
    }

    @Test
    public void getIntPropertyThatIsEmptyShouldReturnNull() {
        //when
        Integer result = this.classUnderTest.getIntProperty(ApplicationPropertyKey.IVS_MESSAGE_TEXT.getKey());
        //then
        assertEquals(null, result);
    }

    @Test
    public void getIntPropertyThatIsEmptyWithDefaultValue() {
        //when
        Integer result = this.classUnderTest.getIntProperty(ApplicationPropertyKey.IVS_MESSAGE_TEXT.getKey(), DEFAULT_INT_VALUE);
        //then
        assertEquals(DEFAULT_INT_VALUE, result);
    }

    @Test
    public void getIntPropertyNotAnInteger() {
        //when
        Integer result = this.classUnderTest.getIntProperty(ApplicationPropertyKey.IVS_SENDER_NAME.getKey());
        //then
        assertEquals(null, result);
    }

    @Test
    public void getIntPropertyNotAnIntegerWithDefaultValue() {
        //when
        Integer result = this.classUnderTest.getIntProperty(ApplicationPropertyKey.IVS_SENDER_NAME.getKey());
        //then
        assertEquals(null, result);
    }

}
