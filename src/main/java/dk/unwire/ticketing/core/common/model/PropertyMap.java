package dk.unwire.ticketing.core.common.model;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.*;
import java.util.HashMap;
import java.util.Map;

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

    protected Map<String, T> getProperties() {
        return this.properties;
    }

    public void addProperty(T property) {
        this.properties.put(property.getName(), property);
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
