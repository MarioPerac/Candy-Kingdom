package factory.server;

import factory.logger.AppLogger;
import factory.properties.ConfigProperties;

import javax.net.ssl.SSLServerSocket;
import javax.net.ssl.SSLServerSocketFactory;
import javax.net.ssl.SSLSocket;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.SocketException;
import java.util.logging.Level;

public class SecureServer extends Thread {

    ConfigProperties prop = new ConfigProperties();

    private String ADDRESS;
    private int PORT;
    private String KEY_STORE_PATH;
    private String KEY_STORE_PASSWORD;

    private static boolean END = false;

    ServerSocket serverSocket;

    public SecureServer() {
        ADDRESS = prop.getServerAddress();
        PORT = prop.getServerPort();
        KEY_STORE_PATH = prop.getKeyStorePath();
        KEY_STORE_PASSWORD = prop.getKeyStorePassword();
        System.setProperty("javax.net.ssl.keyStore", KEY_STORE_PATH);
        System.setProperty("javax.net.ssl.keyStorePassword", KEY_STORE_PASSWORD);
    }

    @Override
    public void run() {

        SSLServerSocketFactory factory = (SSLServerSocketFactory) SSLServerSocketFactory.getDefault();

        try {
            serverSocket = factory.createServerSocket(PORT);
            System.out.println("Server started");
            while (!END) {
                try {
                    SSLSocket socket = (SSLSocket) serverSocket.accept();
                    new SecureServerThread(socket).start();
                } catch (SocketException e) {
                    break;
                }
            }
            System.out.println("Server closed");
        } catch (IOException e) {
            AppLogger.getLogger().log(Level.SEVERE, e.getMessage());
        }

    }

    public void close() {
        try {
            END = true;
            serverSocket.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
