package factory.service;

import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import factory.model.Order;

import java.beans.XMLEncoder;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.TimeoutException;

public class OrderService {

    private final static String QUEUE_NAME = "orders";
    private XmlMapper mapper = new XmlMapper();

    public void create(Order order) {

        try (Connection connection = ConnectionFactoryService.createConnection()) {

            Channel channel = connection.createChannel();
            channel.queueDeclare(QUEUE_NAME, false, false, false, null);

            String orderXML = mapper.writeValueAsString(order);
            channel.basicPublish("", QUEUE_NAME, null, orderXML.getBytes(StandardCharsets.UTF_8));
        } catch (IOException | TimeoutException e) {
            throw new RuntimeException(e);
        }
    }
}
