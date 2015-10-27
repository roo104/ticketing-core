package dk.unwire.ticketing.core.domain.application.enums;

public enum ApplicationPropertyKey {
    IVS_CONTEXT_ID("ivs.context.id"),
    IVS_MESSAGE_TEXT("ivs.message.text"),
    IVS_SENDER_NAME("ivs.sender.name");

    private String key;

    ApplicationPropertyKey(String key) {
        this.key = key;
    }

    public String getKey() {
        return this.key;
    }
}
