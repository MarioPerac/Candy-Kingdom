package factory.properties;

import java.io.IOException;
import java.util.Properties;

public class ConfigProperties {

    Properties properties = new Properties();

    public ConfigProperties() {

        try {
            properties.load(getClass().getResourceAsStream("config.properties"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public String getUsersURL() {
        return properties.getProperty("users_url");
    }

    public String getProductsURL() {
        return properties.getProperty("products_url");
    }
}
