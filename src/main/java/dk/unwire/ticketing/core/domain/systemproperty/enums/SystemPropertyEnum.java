package dk.unwire.ticketing.core.domain.systemproperty.enums;

public enum SystemPropertyEnum {
    IVS_BASEURL("ivs.baseurl");

    private String property;

    SystemPropertyEnum(String property) {
        this.property = property;
    }

    public String getProperty() {
        return this.property;
    }
}
