package factory.properties;

import java.io.IOException;
import java.net.InetAddress;
import java.util.Properties;

public class ConfigProperties {

    private Properties properties = new Properties();

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

    public String getMulticastPort() {
        return properties.getProperty("multicast_port");
    }

    public String getMulticastAddress() {
        return properties.getProperty("multicast_address");
    }

    public String getLogFilePath() {
        return properties.getProperty("log_file");
    }
}
