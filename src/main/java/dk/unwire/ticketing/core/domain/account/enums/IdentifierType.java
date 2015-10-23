package dk.unwire.ticketing.core.domain.account.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public enum IdentifierType {
    MSISDN(1),
    LICENSE_PLATE(4),
    EMAIL(8);
    @Getter
    private int id;

}
