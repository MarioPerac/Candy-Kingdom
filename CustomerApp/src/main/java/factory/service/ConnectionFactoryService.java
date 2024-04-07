package factory.service;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import factory.properties.ConfigProperties;

public class ConnectionFactoryService {

    private static ConfigProperties prop = new ConfigProperties();

    public static Connection createConnection() throws IOException, TimeoutException {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost(prop.getMQHost());
        factory.setUsername(prop.getMQUsername());
        factory.setPassword(prop.getMQPassword());
        return factory.newConnection();
    }
}
