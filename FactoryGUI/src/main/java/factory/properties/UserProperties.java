package factory.properties;

import java.io.IOException;
import java.util.Properties;

public class UserProperties {

    Properties properties = new Properties();

    public UserProperties() {

        try {
            properties.load(getClass().getResourceAsStream("config.properties"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public String getUsersURL() {
        return properties.getProperty("users_url");
    }
}
