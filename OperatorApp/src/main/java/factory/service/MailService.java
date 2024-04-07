package factory.service;

import factory.logger.AppLogger;
import factory.model.Mail;
import factory.model.OrderedProduct;
import factory.properties.ConfigMailProperties;
import jakarta.mail.*;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;

import java.io.*;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.List;
import java.util.logging.Level;

public class MailService {
    private int PORT;
    private String ADDRESS;
    private ConfigMailProperties prop = new ConfigMailProperties();

    public MailService() {
        PORT = Integer.parseInt(prop.getProxyMailServerPort());
        ADDRESS = prop.getProxyMailServerAddres();
    }


    public boolean sendMail(Mail mail) {

        boolean sent = false;
        try (Socket socket = new Socket(InetAddress.getByName(ADDRESS), PORT);
             ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
             ObjectInputStream in = new ObjectInputStream(socket.getInputStream())
        ) {

            out.writeObject(mail);
            out.flush();

            String result = (String) in.readObject();
            sent = "OK".equals(result);

        } catch (IOException | ClassNotFoundException e) {
            AppLogger.getLogger().log(Level.SEVERE, e.getMessage());
        }
        return sent;
    }

}
