package factory.server;

import factory.model.Mail;
import factory.model.OrderedProduct;
import factory.properties.ConfigMailProperties;
import jakarta.mail.*;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class ProxyMailServerThread extends Thread {

    private Socket socket;
    private ObjectOutputStream out;
    private ObjectInputStream in;

    private ConfigMailProperties prop = new ConfigMailProperties();

    public ProxyMailServerThread(Socket socket) {
        this.socket = socket;
        try {
            in = new ObjectInputStream(socket.getInputStream());
            out = new ObjectOutputStream(socket.getOutputStream());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void run() {

        try {

                    Mail mail = (Mail) in.readObject();

                    Session session = Session.getDefaultInstance(prop.getMailProperties(), new Authenticator() {
                        @Override
                        protected PasswordAuthentication getPasswordAuthentication() {
                            return new PasswordAuthentication(prop.getUsername(), prop.getPassword());
                        }
                    });
                    StringBuffer buffer = new StringBuffer();
                    for (OrderedProduct op : mail.getProductList()) {
                        buffer.append(op.toString());
                        buffer.append("\n");
                    }

                    try {
                        Message message = new MimeMessage(session);
                        message.setFrom(new InternetAddress(prop.getUsername()));
                        message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(mail.getTo()));
                        message.setSubject("ORDER STATUS");
                        message.setText("Dear " + mail.getToUsername() + ",\n\n" + "Order is " + mail.getStatus() + " for products: \n" + buffer.toString() + "\nKind regards,\nCandy Kingdom");
                        Transport.send(message);
                        out.writeObject("OK");
                        out.flush();
                    } catch (MessagingException e) {
                        e.printStackTrace();
                        out.writeObject("NOK");
                        out.flush();
                    }


        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                in.close();
                out.close();
                socket.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

        }
    }
}
