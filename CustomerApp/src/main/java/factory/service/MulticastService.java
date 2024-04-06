package factory.service;

import factory.controller.ProductsController;
import factory.listener.MessageListener;
import factory.properties.ConfigProperties;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;

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
            throw new RuntimeException(e);
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
                e.printStackTrace();
            }

            String message = new String(packet.getData(), 0, packet.getLength());
            if (messageListener != null)
                messageListener.onMessageReceived(message);

        }

        System.out.println("Multicast srice finished.");
    }

    public static void setMessageListener(MessageListener listener) {
        messageListener = listener;
    }


    public void stopService() {
        END = true;
    }
}
