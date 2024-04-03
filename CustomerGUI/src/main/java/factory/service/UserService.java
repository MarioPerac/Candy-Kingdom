package factory.service;

import com.google.gson.Gson;
import factory.model.Login;
import factory.model.User;
import factory.model.UserRequest;
import factory.properties.ConfigProperties;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.StandardCharsets;

public class UserService {
    ConfigProperties prop = new ConfigProperties();


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
            out.close();

            return connection.getResponseCode() == HttpURLConnection.HTTP_OK;


        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            if (connection != null)
                connection.disconnect();
        }
    }

    public Login login(UserRequest req) {
        HttpURLConnection connection = null;

        try {
            URL url = new URL(prop.getUsersURL() + "/login");
            connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Content-Type", "application/json");
            connection.setDoOutput(true);

            OutputStream out = connection.getOutputStream();
            out.write(gson.toJson(req).getBytes(StandardCharsets.UTF_8));
            out.close();

            BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));

            Login login = gson.fromJson(in.readLine(), Login.class);
            in.close();

            return login;

        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            if (connection != null)
                connection.disconnect();
        }

    }

    public String getUserEmail(String username) {
        HttpURLConnection connection = null;

        try {
            URL url = new URL(prop.getUsersURL() + "/" + username + "/email");
            connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setRequestProperty("Accept", "application/json");

            BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));

            String email = in.readLine();
            in.close();

            return email;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
