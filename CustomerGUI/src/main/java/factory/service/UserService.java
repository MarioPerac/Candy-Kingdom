package factory.service;

import com.google.gson.Gson;
import factory.LoginApplication;
import factory.model.User;
import factory.properties.UserProperties;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.Properties;

public class UserService {
    UserProperties prop = new UserProperties();


    private final Gson gson = new Gson();

    public boolean register(User user) {


        HttpURLConnection connection = null;
        try {
            URL url = new URL(prop.getUsersURL());
            connection = (HttpURLConnection) url.openConnection();
            connection.setDoOutput(true);
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Content-Type", "application/json");

            OutputStream out = connection.getOutputStream();
            out.write(gson.toJson(user).getBytes(StandardCharsets.UTF_8));
            out.flush();

            return connection.getResponseCode() == HttpURLConnection.HTTP_OK;


        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            if (connection != null)
                connection.disconnect();
        }
    }
}
