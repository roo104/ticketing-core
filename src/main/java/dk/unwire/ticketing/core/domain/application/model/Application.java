package dk.unwire.ticketing.core.domain.application.model;

import dk.unwire.ticketing.core.common.model.PropertyMap;
import lombok.Getter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Application extends PropertyMap<ApplicationProperty> {

    @Getter
    @Id
    @Column(name = "id")
    private int id;
    @Getter
    @Column(name = "shortcode")
    private long shortcode;
    @Getter
    @Column(name = "test_pattern")
    private String testPattern;
    @Getter
    @Column(name = "control_pattern")
    private String controlPattern;
    @Getter
    @Column(name = "verification_pattern")
    private String verificationPattern;
    @Getter
    @Column(name = "country_id")
    private Integer countryId;
    @Getter
    @Column(name = "ttl_min_mo")
    private Integer ttlMinMo;
    @Getter
    @Column(name = "ttl_min_mt")
    private Integer ttlMinMt;
    @Getter
    @Column(name = "verification_timeout")
    private Integer verificationTimeout;
    @Getter
    @Column(name = "vat")
    private String vat;
    @Getter
    @Column(name = "description")
    private String description;
    @Getter
    @Column(name = "gw_username")
    private String gwUsername;
    @Getter
    @Column(name = "gw_password")
    private String gwPassword;
    @Getter
    @Column(name = "test_mediacode")
    private String testMediacode;
    @Getter
    @Column(name = "shortcode_prefix")
    private String shortcodePrefix;
    @Getter
    @Column(name = "gw_validity_min")
    private Integer gwValidityMin;
    @Getter
    @Column(name = "shortcode_id")
    private Integer shortcodeId;
    @Getter
    @Column(name = "action_pattern")
    private String actionPattern;
    @Getter
    @Column(name = "reminder_enabled")
    private Byte reminderEnabled;
    @Getter
    @Column(name = "buy_ticket_type")
    private Integer buyTicketType;
    @Getter
    @Column(name = "application_type")
    private Integer applicationType;
    @Getter
    @Column(name = "time_zone")
    private String timeZone;
    @Getter
    @Column(name = "deleted")
    private Byte deleted;
    @Getter
    @Column(name = "parent")
    private int parent;
    @Getter
    @Column(name = "serialcode_prefix")
    private String serialcodePrefix;

}
