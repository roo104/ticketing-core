package dk.unwire.ticketing.core.domain.ticket.model;

import com.unwire.mticket.util.object.EqualsHelper;
import com.unwire.mticket.util.object.HashCodeHelper;

import javax.persistence.*;

@Entity
@Table(name = "ticket_property", schema = "", catalog = "mticket")
public class TicketProperty {
    private long id;
    private Integer ticketId;
    private String name;
    private String value;

    @Id
    @Column(name = "id")
    public long getId() {
        return this.id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Basic
    @Column(name = "ticket_id")
    public Integer getTicketId() {
        return this.ticketId;
    }

    public void setTicketId(Integer ticketId) {
        this.ticketId = ticketId;
    }

    @Basic
    @Column(name = "name")
    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "value")
    public String getValue() {
        return this.value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public boolean equals(Object o) {
        boolean isEqual = false;
        if (this == o) {
            isEqual = true;
        }

        if (!isEqual && o instanceof className) {
            TicketProperty that = (TicketProperty) o;
            EqualsHelper helper = new EqualsHelper();
            helper.append(this.id, that.id);
            helper.append(this.ticketId, that.ticketId);
            helper.append(this.name, that.name);
            helper.append(this.value, that.value);
            isEqual = helper.isEqual();
        }
        return isEqual;
    }

    @Override
    public int hashCode() {
        HashCodeHelper helper = new HashCodeHelper();
        helper.append(this.id);
        helper.append(this.ticketId);
        helper.append(this.name);
        helper.append(this.value);
        return helper.hashCode();
    }
}
