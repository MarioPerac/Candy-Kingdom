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

    public String getKeyStorePath() {
        return properties.getProperty("keystore_path");
    }

    public String getKeyStorePassword() {
        return properties.getProperty("keystore_password");
    }

    public String getServerAddress() {
        return properties.getProperty("server_address");
    }

    public int getServerPort() {
        return Integer.parseInt(properties.getProperty("server_port"));
    }

    public String getFactoryUsersPath() {
        return properties.getProperty("factory_users_path");
    }

    public String getOrdersPath() {
        return properties.getProperty("orders_path");
    }

    public String getMulticastPort() {
        return properties.getProperty("multicast_port");
    }

    public String getMulticastAddress() {
        return properties.getProperty("multicast_address");
    }
}
