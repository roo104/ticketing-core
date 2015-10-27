package dk.unwire.ticketing.core.domain.otp;

public final class OtpConstants {
    public static final int WIREMOCK_PORT = 7070;
    public static final String IVS_REQUEST_RESOURCE = "/context/1/validation/identity/12345678";
    public static final String IVS_CONFIRM_RESOURCE = "/context/1/confirmation/";
    public static final String EXPECTED_CONFIRMATION_REQUEST = "{\"identity\":\"12345678\",\"password\":\"1234\"}";
    public static final String IVS_BASE_URL = "http://localhost:" + WIREMOCK_PORT;

}
