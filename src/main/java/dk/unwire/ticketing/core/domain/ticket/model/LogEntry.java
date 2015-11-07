package dk.unwire.ticketing.core.domain.ticket.model;

import lombok.Getter;

import javax.persistence.*;

@Entity
@Table(name = "log_entry")
public class LogEntry {

    public static final int LOG_TYPE_PLATFORM_OPERATION = 101;

    @Id
    @Getter
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;
    @Getter
    @Column(name = "action")
    private int action;
    @Getter
    @Column(name = "message")
    private String message;
    @Getter
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ticket_state_id", referencedColumnName = "id")
    private TicketState ticketState;
    @Getter
    @Column(name = "type")
    private int type;
    @Getter
    @Column(name = "user_dn")
    private String userDn;
    @Getter
    @Column(name = "error_code")
    private int errorCode;
    @Getter
    @Column(name = "nano_time")
    private long nanoTime;

    private LogEntry() {
    }

    protected LogEntry(TicketState ticketState) {
        this.ticketState = ticketState;
        this.type = LOG_TYPE_PLATFORM_OPERATION;
        this.nanoTime = System.nanoTime();
    }

    protected LogEntry(TicketState ticketState, int errorCode) {
        this(ticketState);
        this.errorCode = errorCode;
    }

}
