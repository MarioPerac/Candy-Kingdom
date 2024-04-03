package factory.server;

import factory.repository.OperatorRepository;

import javax.net.ssl.SSLSocket;
import java.io.*;

public class SecureServerThread extends Thread {


    private SSLSocket socket;
    private PrintWriter out;
    private BufferedReader in;

    OperatorRepository operatorRepository;

    public SecureServerThread(SSLSocket socket) {
        this.socket = socket;
        operatorRepository = new OperatorRepository();
        try {
            out = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()), true);
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        } catch (IOException e) {
            e.printStackTrace();
        }
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
            }
            in.close();
            out.close();
            socket.close();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }


    }
}
