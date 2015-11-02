package dk.unwire.ticketing.core.domain.ticket.model;

import lombok.Getter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "log_entry")
public class LogEntry {

    @Id
    @Getter
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;
    @Getter
    @Column(name = "timestamp")
    private LocalDateTime timestamp;
    @Getter
    @Column(name = "action")
    private Integer action;
    @Getter
    @Column(name = "message")
    private String message;
    @Getter
    @Column(name = "ticket_state")
    private Integer ticketState;
    @Getter
    @Column(name = "ticket_state_id")
    private Long ticketStateId;
    @Getter
    @Column(name = "type")
    private Integer type;
    @Getter
    @Column(name = "user_dn")
    private String userDn;
    @Getter
    @Column(name = "application_id")
    private Integer applicationId;
    @Getter
    @Column(name = "mo_message_id")
    private Long moMessageId;
    @Getter
    @Column(name = "mt_message_id")
    private Long mtMessageId;
    @Getter
    @Column(name = "ticket_checker_id")
    private Integer ticketCheckerId;
    @Getter
    @Column(name = "error_code")
    private Integer errorCode;
    @Getter
    @Column(name = "nano_time")
    private Long nanoTime;

}
