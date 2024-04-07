package factory.server;

import factory.logger.AppLogger;
import factory.properties.ConfigProperties;
import factory.repository.OperatorRepository;

import javax.net.ssl.SSLSocket;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.PrivateKey;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Timer;
import java.util.logging.Level;

public class SecureServerThread extends Thread {


    private SSLSocket socket;
    private PrintWriter out;
    private BufferedReader in;

    private String ORDERS_PATH;
    private ConfigProperties prop = new ConfigProperties();
    private OperatorRepository operatorRepository;

    public SecureServerThread(SSLSocket socket) {
        this.socket = socket;
        operatorRepository = new OperatorRepository();
        try {
            out = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()), true);
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        } catch (IOException e) {
            AppLogger.getLogger().log(Level.SEVERE, e.getMessage());
            
        }
        ORDERS_PATH = prop.getOrdersPath();
    }


    @Override
    public void run() {

        try {
            String request = in.readLine();
            if ("LOGIN".equals(request)) {

                String name = in.readLine();
                if (operatorRepository.authentication(name)) {
                    out.println("OK");
                } else {
                    out.println("NOK");
                }
            } else if ("ORDER".equals(request)) {
                LocalDateTime now = LocalDateTime.now();
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd_HH-mm-ss");
                String formattedDateTime = now.format(formatter);
                String filePath = ORDERS_PATH + File.separator + File.separator + "ORDER_" + formattedDateTime + ".txt";
                Path path = Paths.get(filePath);
                Files.createFile(path);
                String line = "";

                PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(filePath)));
                while (!(line = in.readLine()).equals("#")) {
                    out.println(line);
                }

                out.close();

                out.println("OK");
            }

        } catch (IOException e) {
            AppLogger.getLogger().log(Level.SEVERE, e.getMessage());
            out.println("NOK");
        } finally {
            try {
                in.close();
                out.close();
                socket.close();
            } catch (IOException e) {
                AppLogger.getLogger().log(Level.SEVERE, e.getMessage());
            }

        }


    }
}
