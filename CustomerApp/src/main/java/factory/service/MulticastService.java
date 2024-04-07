package factory.service;


import factory.listener.MessageListener;
import factory.logger.AppLogger;
import factory.properties.ConfigProperties;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.util.logging.Level;

public class MulticastService extends Thread {

    private ConfigProperties prop = new ConfigProperties();
    private int PORT;
    private InetAddress ADDRESS;

    private MulticastSocket client;

    private boolean END = false;
    private static MessageListener messageListener;

    public MulticastService() {
        PORT = Integer.parseInt(prop.getMulticastPort());
        try {
            ADDRESS = InetAddress.getByName(prop.getMulticastAddress());
            client = new MulticastSocket(PORT);
            client.joinGroup(ADDRESS);
        } catch (IOException e) {
            AppLogger.getLogger().log(Level.SEVERE, e.getMessage());
        }
    }

    @Override
    public void run() {

        byte[] buffer = new byte[256];
        DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
        System.out.println("Multicast service started.");

        while (!END) {

            try {
                client.receive(packet);
            } catch (IOException e) {
                AppLogger.getLogger().log(Level.SEVERE, e.getMessage());
            }

            String message = new String(packet.getData(), 0, packet.getLength());
            if (messageListener != null)
                messageListener.onMessageReceived(message);

        }

        System.out.println("Multicast service finished.");
    }

    public static void setMessageListener(MessageListener listener) {
        messageListener = listener;
    }


    public void stopService() {
        END = true;
    }
}
