package dk.unwire.ticketing.core.domain.account.enums;

public enum IdentifierType {

	MSISDN(1),
    LICENSE_PLATE(4),
    EMAIL(8);

	IdentifierType() {

	}

	IdentifierType(int id) {
		this.id = id;
	}

    private int id;

	public int getId() {
		return this.id;
	}
}
