package dk.unwire.ticketing.core.domain.application.model;

import dk.unwire.ticketing.core.domain.application.enums.ApplicationPropertyKey;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.*;
import java.util.HashMap;
import java.util.Map;

@Entity
public class Application {
    private static final Logger logger = LoggerFactory.getLogger(Application.class);

    @Id
    @Column(name = "id")
    private int id;
    @Basic
    @Column(name = "shortcode")
    private long shortcode;
    @Basic
    @Column(name = "test_pattern")
    private String testPattern;
    @Basic
    @Column(name = "control_pattern")
    private String controlPattern;
    @Basic
    @Column(name = "verification_pattern")
    private String verificationPattern;
    @Basic
    @Column(name = "country_id")
    private Integer countryId;
    @Basic
    @Column(name = "ttl_min_mo")
    private Integer ttlMinMo;
    @Basic
    @Column(name = "ttl_min_mt")
    private Integer ttlMinMt;
    @Basic
    @Column(name = "verification_timeout")
    private Integer verificationTimeout;
    @Basic
    @Column(name = "vat")
    private String vat;
    @Basic
    @Column(name = "description")
    private String description;
    @Basic
    @Column(name = "gw_username")
    private String gwUsername;
    @Basic
    @Column(name = "gw_password")
    private String gwPassword;
    @Basic
    @Column(name = "test_mediacode")
    private String testMediacode;
    @Basic
    @Column(name = "shortcode_prefix")
    private String shortcodePrefix;
    @Basic
    @Column(name = "gw_validity_min")
    private Integer gwValidityMin;
    @Basic
    @Column(name = "shortcode_id")
    private Integer shortcodeId;
    @Basic
    @Column(name = "action_pattern")
    private String actionPattern;
    @Basic
    @Column(name = "reminder_enabled")
    private Byte reminderEnabled;
    @Basic
    @Column(name = "buy_ticket_type")
    private Integer buyTicketType;
    @Basic
    @Column(name = "application_type")
    private Integer applicationType;
    @Basic
    @Column(name = "time_zone")
    private String timeZone;
    @Basic
    @Column(name = "deleted")
    private Byte deleted;
    @Basic
    @Column(name = "parent")
    private int parent;
    @Basic
    @Column(name = "serialcode_prefix")
    private String serialcodePrefix;
    @Basic
    @Column(name = "account_source")
    private String accountSource;

    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "application_id", referencedColumnName = "id")
    @MapKeyColumn(name = "name")
    private Map<String, ApplicationProperty> applicationProperties;

    public Application() {
        this.applicationProperties = new HashMap<>();
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setApplicationProperties(Map<String, ApplicationProperty> applicationProperties) {
        this.applicationProperties = applicationProperties;
    }

    /**
     * Get Application property as string.
     *
     * @param keyName Key used to get value from map
     * @return If value is empty null will be returned.
     */
    public String getStringApplicationProperty(ApplicationPropertyKey keyName) {
        String result;
        ApplicationProperty property = this.applicationProperties.get(keyName.getPropertyKey());
        if (property == null) {
            result = null;
        } else {
            result = property.getValue();
        }
        return result;
    }

    /**
     * Get Application property as string.
     *
     * @param keyName      Key used to get value from map
     * @param defaultValue
     * @return If value is empty defaultValue will be returned.
     */
    public String getStringApplicationProperty(ApplicationPropertyKey keyName, String defaultValue) {
        String result = defaultValue;
        ApplicationProperty property = this.applicationProperties.get(keyName.getPropertyKey());
        if (property != null) {
            result = property.getValue();
        }
        return result;
    }

    /**
     * Get Application property as Integer.
     *
     * @param keyName Key used to get value from map
     * @return Return type is Integer. If value is empty null will be returned.
     */
    public Integer getIntApplicationProperty(ApplicationPropertyKey keyName) {
        Integer result = null;
        ApplicationProperty property = this.applicationProperties.get(keyName.getPropertyKey());
        if (property != null) {
            try {
                result = Integer.parseInt(property.getValue());
            } catch (NumberFormatException e) {
                logger.debug("Could not parse ApplicationProperty with key [{}] and value [{}] to integer", property.getName(), property.getValue());
            }

        }
        return result;
    }

    /**
     * Get Application property as Integer.
     *
     * @param keyName      Key used to get value from map
     * @param defaultValue
     * @return Return type is Integer. If value is empty defaultValue will be returned.
     */
    public Integer getIntApplicationProperty(ApplicationPropertyKey keyName, int defaultValue) {
        Integer result = defaultValue;
        ApplicationProperty property = this.applicationProperties.get(keyName.getPropertyKey());
        if (property != null) {
            try {
                result = Integer.parseInt(property.getValue());
            } catch (NumberFormatException e) {
                logger.debug("Could not parse ApplicationProperty with key [{}] and value [{}] to integer", property.getName(), property.getValue());
            }
        }
        return result;
    }
}
