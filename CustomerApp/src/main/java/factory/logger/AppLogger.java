package factory.logger;

import factory.CustomerApplication;
import factory.properties.ConfigProperties;

import java.io.IOException;
import java.util.logging.Logger;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class AppLogger {

    private static Logger LOGGER = null;
    private static ConfigProperties prop = new ConfigProperties();

    public static Logger getLogger() {
        if (LOGGER == null) {
            try {
                LOGGER = Logger.getLogger(CustomerApplication.class.getName());
                FileHandler fileHandler = new FileHandler(prop.getLogFilePath());
                fileHandler.setFormatter(new SimpleFormatter());
                LOGGER.addHandler(fileHandler);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        return LOGGER;
    }
}
