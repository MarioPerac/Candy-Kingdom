package factory.service;

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

public class MailService {
    private int PORT;
    private String ADDRESS;
    private ConfigMailProperties prop = new ConfigMailProperties();

    public MailService() {
        PORT = Integer.parseInt(prop.getProxyMailServerPort());
        ADDRESS = prop.getProxyMailServerAddres();
    }


    public boolean sendMail(Mail mail) {

        try (Socket socket = new Socket(InetAddress.getByName(ADDRESS), PORT);
             ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
             ObjectInputStream in = new ObjectInputStream(socket.getInputStream())
        ) {

            out.writeObject(mail);
            out.flush();

            String result = (String) in.readObject();
            if ("OK".equals(result)) {
                return true;
            } else
                return false;
        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

    }

}
