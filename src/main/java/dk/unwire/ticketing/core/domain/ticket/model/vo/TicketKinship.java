package dk.unwire.ticketing.core.domain.ticket.model.vo;

import lombok.Getter;

public enum TicketKinship {

    None(0), Child(1), Parent(2), Hidden_Parent(3);

    TicketKinship(int type) {
        this.type = type;
    }

    @Getter
    private int type;

    public static TicketKinship fromValue(int kinshipValue) {
        TicketKinship ticketKinship = null;

        for (TicketKinship kinship : TicketKinship.values()) {
            if (kinship.type == kinshipValue) {
                ticketKinship = kinship;
                break;
            }
        }
        return ticketKinship;
    }
}
