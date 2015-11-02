package dk.unwire.ticketing.core.domain.ticket.model;

import com.unwire.mticket.util.object.EqualsHelper;
import com.unwire.mticket.util.object.HashCodeHelper;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.sql.Timestamp;

@Entity
@javax.persistence.Table(name = "log_entry", schema = "", catalog = "mticket")
public class LogEntry {
    private long id;

    @Id
    @javax.persistence.Column(name = "id")
    public long getId() {
        return this.id;
    }

    public void setId(long id) {
        this.id = id;
    }

    private Timestamp timestamp;

    @Basic
    @javax.persistence.Column(name = "timestamp")
    public Timestamp getTimestamp() {
        return this.timestamp;
    }

    public void setTimestamp(Timestamp timestamp) {
        this.timestamp = timestamp;
    }

    private Integer action;

    @Basic
    @javax.persistence.Column(name = "action")
    public Integer getAction() {
        return this.action;
    }

    public void setAction(Integer action) {
        this.action = action;
    }

    private String message;

    @Basic
    @javax.persistence.Column(name = "message")
    public String getMessage() {
        return this.message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    private Integer ticketState;

    @Basic
    @javax.persistence.Column(name = "ticket_state")
    public Integer getTicketState() {
        return this.ticketState;
    }

    public void setTicketState(Integer ticketState) {
        this.ticketState = ticketState;
    }

    private Long ticketStateId;

    @Basic
    @javax.persistence.Column(name = "ticket_state_id")
    public Long getTicketStateId() {
        return this.ticketStateId;
    }

    public void setTicketStateId(Long ticketStateId) {
        this.ticketStateId = ticketStateId;
    }

    private Integer type;

    @Basic
    @javax.persistence.Column(name = "type")
    public Integer getType() {
        return this.type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    private Long ticketId;

    @Basic
    @javax.persistence.Column(name = "ticket_id")
    public Long getTicketId() {
        return this.ticketId;
    }

    public void setTicketId(Long ticketId) {
        this.ticketId = ticketId;
    }

    private String userDn;

    @Basic
    @javax.persistence.Column(name = "user_dn")
    public String getUserDn() {
        return this.userDn;
    }

    public void setUserDn(String userDn) {
        this.userDn = userDn;
    }

    private Integer applicationId;

    @Basic
    @javax.persistence.Column(name = "application_id")
    public Integer getApplicationId() {
        return this.applicationId;
    }

    public void setApplicationId(Integer applicationId) {
        this.applicationId = applicationId;
    }

    private Long moMessageId;

    @Basic
    @javax.persistence.Column(name = "mo_message_id")
    public Long getMoMessageId() {
        return this.moMessageId;
    }

    public void setMoMessageId(Long moMessageId) {
        this.moMessageId = moMessageId;
    }

    private Long mtMessageId;

    @Basic
    @javax.persistence.Column(name = "mt_message_id")
    public Long getMtMessageId() {
        return this.mtMessageId;
    }

    public void setMtMessageId(Long mtMessageId) {
        this.mtMessageId = mtMessageId;
    }

    private Integer ticketCheckerId;

    @Basic
    @javax.persistence.Column(name = "ticket_checker_id")
    public Integer getTicketCheckerId() {
        return this.ticketCheckerId;
    }

    public void setTicketCheckerId(Integer ticketCheckerId) {
        this.ticketCheckerId = ticketCheckerId;
    }

    private Integer errorCode;

    @Basic
    @javax.persistence.Column(name = "error_code")
    public Integer getErrorCode() {
        return this.errorCode;
    }

    public void setErrorCode(Integer errorCode) {
        this.errorCode = errorCode;
    }

    private Long nanoTime;

    @Basic
    @javax.persistence.Column(name = "nano_time")
    public Long getNanoTime() {
        return this.nanoTime;
    }

    public void setNanoTime(Long nanoTime) {
        this.nanoTime = nanoTime;
    }

    @Override
    public boolean equals(Object o) {
        boolean isEqual = false;
        if (this == o) {
            isEqual = true;
        }

        if (!isEqual && o instanceof className) {
            LogEntry logEntry = (LogEntry) o;
            EqualsHelper helper = new EqualsHelper();
            helper.append(this.id, logEntry.id);
            helper.append(this.timestamp, logEntry.timestamp);
            helper.append(this.action, logEntry.action);
            helper.append(this.message, logEntry.message);
            helper.append(this.ticketState, logEntry.ticketState);
            helper.append(this.ticketStateId, logEntry.ticketStateId);
            helper.append(this.type, logEntry.type);
            helper.append(this.ticketId, logEntry.ticketId);
            helper.append(this.userDn, logEntry.userDn);
            helper.append(this.applicationId, logEntry.applicationId);
            helper.append(this.moMessageId, logEntry.moMessageId);
            helper.append(this.mtMessageId, logEntry.mtMessageId);
            helper.append(this.ticketCheckerId, logEntry.ticketCheckerId);
            helper.append(this.errorCode, logEntry.errorCode);
            helper.append(this.nanoTime, logEntry.nanoTime);
            isEqual = helper.isEqual();
        }
        return isEqual;
    }

    @Override
    public int hashCode() {
        HashCodeHelper helper = new HashCodeHelper();
        helper.append(this.id);
        helper.append(this.timestamp);
        helper.append(this.action);
        helper.append(this.message);
        helper.append(this.ticketState);
        helper.append(this.ticketStateId);
        helper.append(this.type);
        helper.append(this.ticketId);
        helper.append(this.userDn);
        helper.append(this.applicationId);
        helper.append(this.moMessageId);
        helper.append(this.mtMessageId);
        helper.append(this.ticketCheckerId);
        helper.append(this.errorCode);
        helper.append(this.nanoTime);
        return helper.hashCode();
    }
}
