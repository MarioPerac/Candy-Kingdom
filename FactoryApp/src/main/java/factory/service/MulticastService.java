package factory.service;

import factory.logger.AppLogger;
import factory.properties.ConfigProperties;

import java.io.IOException;
import java.io.ObjectInputFilter;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.util.Properties;
import java.util.logging.Level;

public class MulticastService {
    private ConfigProperties prop = new ConfigProperties();
    private int PORT;
    private InetAddress ADDRESS;

    private MulticastSocket server;

    public MulticastService() {
        PORT = Integer.parseInt(prop.getMulticastPort());
        try {
            ADDRESS = InetAddress.getByName(prop.getMulticastAddress());
            server = new MulticastSocket();
            server.joinGroup(ADDRESS);
        } catch (IOException e) {
            AppLogger.getLogger().log(Level.SEVERE, e.getMessage());
        }
    }

    public void sendMessage(String message) {
        byte[] data = message.getBytes();
        DatagramPacket packet = new DatagramPacket(data, data.length, ADDRESS, PORT);
        try {
            server.send(packet);
        } catch (IOException e) {
            AppLogger.getLogger().log(Level.SEVERE, e.getMessage());
        }
    }
}
