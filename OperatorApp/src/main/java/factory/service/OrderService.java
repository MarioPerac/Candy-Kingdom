package factory.service;

import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.rabbitmq.client.*;
import factory.logger.AppLogger;
import factory.model.Order;

import java.io.IOException;
import java.util.concurrent.TimeoutException;
import java.util.logging.Level;


public class OrderService {

    private final static String QUEUE_NAME = "orders";
    private XmlMapper mapper = new XmlMapper();

    public Order get() {
        try (Connection connection = ConnectionFactoryService.createConnection()) {
            Channel channel = connection.createChannel();
            channel.queueDeclare(QUEUE_NAME, true, false, false, null);


            GetResponse response = channel.basicGet(QUEUE_NAME, false);

            if (response != null) {

                String message = new String(response.getBody(), "UTF-8");
                Order order = mapper.readValue(message, Order.class);

                channel.basicAck(response.getEnvelope().getDeliveryTag(), false);

                return order;
            }

        } catch (IOException | TimeoutException e) {
            AppLogger.getLogger().log(Level.SEVERE, e.getMessage());
        }

        return null;
    }
}
