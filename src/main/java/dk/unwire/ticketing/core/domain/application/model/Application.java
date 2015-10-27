package dk.unwire.ticketing.core.domain.application.model;

import dk.unwire.ticketing.core.domain.application.enums.ApplicationPropertyKey;
import lombok.Getter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.*;
import java.util.HashMap;
import java.util.Map;

@Entity
public class Application {
    private static final Logger logger = LoggerFactory.getLogger(Application.class);
    @Getter
    @Id
    @Column(name = "id")
    private int id;
    @Basic
    @Column(name = "shortcode")
    @Getter
    private long shortcode;
    @Basic
    @Column(name = "test_pattern")
    @Getter
    private String testPattern;
    @Basic
    @Column(name = "control_pattern")
    @Getter
    private String controlPattern;
    @Basic
    @Column(name = "verification_pattern")
    @Getter
    private String verificationPattern;
    @Basic
    @Column(name = "country_id")
    @Getter
    private Integer countryId;
    @Basic
    @Column(name = "ttl_min_mo")
    @Getter
    private Integer ttlMinMo;
    @Basic
    @Column(name = "ttl_min_mt")
    @Getter
    private Integer ttlMinMt;
    @Basic
    @Column(name = "verification_timeout")
    @Getter
    private Integer verificationTimeout;
    @Basic
    @Column(name = "vat")
    @Getter
    private String vat;
    @Basic
    @Column(name = "description")
    @Getter
    private String description;
    @Basic
    @Column(name = "gw_username")
    @Getter
    private String gwUsername;
    @Basic
    @Column(name = "gw_password")
    @Getter
    private String gwPassword;
    @Basic
    @Column(name = "test_mediacode")
    @Getter
    private String testMediacode;
    @Basic
    @Column(name = "shortcode_prefix")
    @Getter
    private String shortcodePrefix;
    @Basic
    @Column(name = "gw_validity_min")
    @Getter
    private Integer gwValidityMin;
    @Basic
    @Column(name = "shortcode_id")
    @Getter
    private Integer shortcodeId;
    @Basic
    @Column(name = "action_pattern")
    @Getter
    private String actionPattern;
    @Basic
    @Column(name = "reminder_enabled")
    @Getter
    private Byte reminderEnabled;
    @Basic
    @Column(name = "buy_ticket_type")
    @Getter
    private Integer buyTicketType;
    @Basic
    @Column(name = "application_type")
    @Getter
    private Integer applicationType;
    @Basic
    @Column(name = "time_zone")
    @Getter
    private String timeZone;
    @Basic
    @Column(name = "deleted")
    @Getter
    private Byte deleted;
    @Basic
    @Column(name = "parent")
    @Getter
    private int parent;
    @Basic
    @Column(name = "serialcode_prefix")
    @Getter
    private String serialcodePrefix;
    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "application_id", referencedColumnName = "id")
    @MapKeyColumn(name = "name")
    private Map<String, ApplicationProperty> applicationProperties;

    public Application() {
        this.applicationProperties = new HashMap<>();
    }

    protected void setApplicationProperties(Map<String, ApplicationProperty> applicationProperties) {
        this.applicationProperties = applicationProperties;
    }

    /**
     * Get Application property as string.
     *
     * @param keyName Key used to get value from map
     * @return If value is empty null will be returned.
     */
    public String getStringApplicationProperty(ApplicationPropertyKey keyName) {
        String result = null;
        ApplicationProperty property = this.applicationProperties.get(keyName.getPropertyKey());
        if (property != null) {
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
                logger.debug("Could not parse ApplicationProperty with key [{}] and value [{}] to integer - Using provided default value [{}]", property.getName(), property.getValue(), defaultValue);
            }
        }
        return result;
    }
}
