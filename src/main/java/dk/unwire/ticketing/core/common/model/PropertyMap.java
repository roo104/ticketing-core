package dk.unwire.ticketing.core.common.model;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.*;
import java.util.HashMap;
import java.util.Map;

/**
 * Extending this class will provide a Map of properties and a long list of helper methods to retrieve property values as int and string.
 * To set the correct DB mapping add an annotation to the top of the class like:
 * @AssociationOverride(name = "properties", joinColumns = @JoinColumn(name = "your_referring_id", referencedColumnName = "id"))
 * Change "your_referring_id" to the name of the column on the property table.
 * For application_property table, "your_referring_id" will be "application_id".
 * @param <T> Class who extends Property
 */
@MappedSuperclass
public class PropertyMap<T extends Property> {

    private static final Logger logger = LoggerFactory.getLogger(PropertyMap.class);

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(referencedColumnName = "id")
    @MapKeyColumn(name = "name")
    protected final Map<String, T> properties;

    protected PropertyMap() {
        this.properties = new HashMap<>();
    }

    /**
     * Use this to overwritten the default HashMap type.
     */
    protected PropertyMap(Map<String, T> map) {
        this.properties = map;
    }

    public void addProperty(T property) {
        this.properties.put(property.getName(), property);
    }

    public void removeProperty(T property) {
        this.properties.remove(property.getName(), property);
    }

    /**
     * Get property as string.
     *
     * @param keyName Key used to get value from map
     * @return If value is empty null will be returned.
     */
    public String getStringProperty(String keyName) {
        String result = null;
        Property property = this.properties.get(keyName);
        if (property != null) {
            result = property.getValue();
        }
        return result;
    }

    /**
     * Get property as string.
     *
     * @param keyName      Key used to get value from map
     * @param defaultValue
     * @return If value is empty defaultValue will be returned.
     */
    public String getStringProperty(String keyName, String defaultValue) {
        String result = defaultValue;
        Property property = this.properties.get(keyName);
        if (property != null) {
            result = property.getValue();
        }
        return result;
    }

    /**
     * Get property as Integer.
     *
     * @param keyName Key used to get value from map
     * @return Return type is Integer. If value is empty null will be returned.
     */
    public Integer getIntProperty(String keyName) {
        Integer result = null;
        Property property = this.properties.get(keyName);
        if (property != null) {
            try {
                result = Integer.parseInt(property.getValue());
            } catch (NumberFormatException e) {
                logger.debug("Could not parse Property with key [{}] and value [{}] to integer", property.getName(), property.getValue());
            }

        }
        return result;
    }

    /**
     * Get property as Integer.
     *
     * @param keyName      Key used to get value from map
     * @param defaultValue
     * @return Return type is Integer. If value is empty defaultValue will be returned.
     */
    public Integer getIntProperty(String keyName, int defaultValue) {
        Integer result = defaultValue;
        Property property = this.properties.get(keyName);
        if (property != null) {
            try {
                result = Integer.parseInt(property.getValue());
            } catch (NumberFormatException e) {
                logger.debug("Could not parse Property with key [{}] and value [{}] to integer", property.getName(), property.getValue());
            }
        }
        return result;
    }
}
