package factory.service;

import factory.properties.ConfigProperties;

import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;
import java.io.*;
import java.net.InetAddress;

public class OperatorService {

    private String ADDRESS;
    private int PORT;
    private String KEY_STORE_PATH;
    private String KEY_STORE_PASSWORD;
    ConfigProperties prop = new ConfigProperties();

    public OperatorService(){
        ADDRESS = prop.getServerAddress();
        PORT = prop.getServerPort();
        KEY_STORE_PATH = prop.getTrustStorePath();
        KEY_STORE_PASSWORD = prop.getTrustStorePassword();

        System.setProperty("javax.net.ssl.trustStore", KEY_STORE_PATH);
        System.setProperty("javax.net.ssl.trustStorePassword", KEY_STORE_PASSWORD);
    }
    public boolean authentication(String username){

        SSLSocketFactory factory = (SSLSocketFactory) SSLSocketFactory.getDefault();
        try (SSLSocket socket = (SSLSocket) factory.createSocket(InetAddress.getByName(ADDRESS), PORT);
             PrintWriter out = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()), true);
             BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()))
        ){
            out.println("LOGIN");
            out.println(username);
            String success = in.readLine();

            System.out.println(success);
            if("OK".equals(success))
            {
                return true;
            }
            else if("NOK".equals(success))
            {
                return  false;
            }else{
                throw new RuntimeException("Authentication failed.");
            }


        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
