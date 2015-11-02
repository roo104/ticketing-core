package dk.unwire.ticketing.core.domain.ticket.model;

import com.unwire.mticket.util.object.EqualsHelper;
import com.unwire.mticket.util.object.HashCodeHelper;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "ticket_state", schema = "", catalog = "mticket")
public class TicketState {
    private int id;
    private int ticketState;
    private int transactionState;
    private int action;
    private int errorCode;
    private Timestamp timestamp;
    private int ticketId;

    @Id
    @Column(name = "id")
    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "ticket_state")
    public int getTicketState() {
        return this.ticketState;
    }

    public void setTicketState(int ticketState) {
        this.ticketState = ticketState;
    }

    @Basic
    @Column(name = "transaction_state")
    public int getTransactionState() {
        return this.transactionState;
    }

    public void setTransactionState(int transactionState) {
        this.transactionState = transactionState;
    }

    @Basic
    @Column(name = "action")
    public int getAction() {
        return this.action;
    }

    public void setAction(int action) {
        this.action = action;
    }

    @Basic
    @Column(name = "error_code")
    public int getErrorCode() {
        return this.errorCode;
    }

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }

    @Basic
    @Column(name = "timestamp")
    public Timestamp getTimestamp() {
        return this.timestamp;
    }

    public void setTimestamp(Timestamp timestamp) {
        this.timestamp = timestamp;
    }

    @Basic
    @Column(name = "ticket_id")
    public int getTicketId() {
        return this.ticketId;
    }

    public void setTicketId(int ticketId) {
        this.ticketId = ticketId;
    }

    @Override
    public boolean equals(Object o) {
        boolean isEqual = false;
        if (this == o) {
            isEqual = true;
        }

        if (!isEqual && o instanceof className) {
            TicketState that = (TicketState) o;
            EqualsHelper helper = new EqualsHelper();
            helper.append(this.id, that.id);
            helper.append(this.ticketState, that.ticketState);
            helper.append(this.transactionState, that.transactionState);
            helper.append(this.action, that.action);
            helper.append(this.errorCode, that.errorCode);
            helper.append(this.ticketId, that.ticketId);
            helper.append(this.timestamp, that.timestamp);
            isEqual = helper.isEqual();
        }
        return isEqual;
    }

    @Override
    public int hashCode() {
        HashCodeHelper helper = new HashCodeHelper();
        helper.append(this.id);
        helper.append(this.ticketState);
        helper.append(this.transactionState);
        helper.append(this.action);
        helper.append(this.errorCode);
        helper.append(this.timestamp);
        helper.append(this.ticketId);
        return helper.hashCode();
    }
}
