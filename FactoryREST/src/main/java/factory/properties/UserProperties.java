package factory.properties;

import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class UserProperties {

    private String USERS_PATH;

    public UserProperties() {
        Properties prop = new Properties();
        try {
            prop.load(getClass().getResourceAsStream("config.properties"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        USERS_PATH = prop.getProperty("users_path");
        
    }

    public String getUsersPath() {
        return USERS_PATH;
    }
}
