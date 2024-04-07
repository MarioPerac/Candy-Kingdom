package factory.service;

import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import factory.logger.AppLogger;
import factory.model.Order;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.TimeoutException;
import java.util.logging.Level;

public class OrderService {

    private final static String QUEUE_NAME = "orders";
    private XmlMapper mapper = new XmlMapper();

    public void create(Order order) {

        try (Connection connection = ConnectionFactoryService.createConnection()) {

            Channel channel = connection.createChannel();
            channel.queueDeclare(QUEUE_NAME, true, false, false, null);

            String orderXML = mapper.writeValueAsString(order);
            System.out.println(orderXML);
            channel.basicPublish("", QUEUE_NAME, null, orderXML.getBytes(StandardCharsets.UTF_8));
        } catch (IOException | TimeoutException e) {
            AppLogger.getLogger().log(Level.SEVERE, e.getMessage());
        }
    }
}
