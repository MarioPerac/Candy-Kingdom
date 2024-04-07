package factory.properties;

import java.io.IOException;
import java.net.URL;
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


    public String getTrustStorePath() {
        return properties.getProperty("truststore_path");
    }

    public String getTrustStorePassword() {
        return properties.getProperty("truststore_password");
    }

    public String getServerAddress() {
        return properties.getProperty("server_address");
    }

    public int getServerPort() {
        return Integer.parseInt(properties.getProperty("server_port"));
    }

    public String getProductsURL() {

        return properties.getProperty("products_url");
    }
}
