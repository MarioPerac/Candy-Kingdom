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

    public String getLogFilePath() {
        return properties.getProperty("log_file");
    }

    public String getRegistryPort() {
        return properties.getProperty("registry_port");
    }
}
