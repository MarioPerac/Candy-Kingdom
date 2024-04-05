package factory.properties;

import java.io.IOException;
import java.util.Properties;

public class ConfigMailProperties {

    Properties properties = new Properties();

    public ConfigMailProperties() {

        try {
            properties.load(getClass().getResourceAsStream("configMail.properties"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    
    public String getProxyMailServerAddres() {
        return properties.getProperty("proxy_server_address");
    }

    public String getProxyMailServerPort() {
        return properties.getProperty("proxy_server_port");
    }
}
