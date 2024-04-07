package factory.service;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import factory.properties.ConfigProperties;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.TimeoutException;

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
