package factory.service;

import com.google.gson.Gson;
import factory.model.StatusRequest;
import factory.model.User;
import factory.properties.ConfigProperties;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class UserService {

    private final ConfigProperties prop = new ConfigProperties();
    private final Gson gson = new Gson();
    public List<User> getAll(){

        HttpURLConnection connection = null;
        try {
            URL url = new URL(prop.getUsersURL());
            connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setRequestProperty("Accept", "application/json");

            BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            StringBuffer jsonContent = new StringBuffer();

            String line = "";
            while((line = in.readLine()) != null){
                jsonContent.append(line);
            }

           User[] users =  gson.fromJson(jsonContent.toString(), User[].class);
            return new ArrayList<>(Arrays.asList(users));

        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            if (connection != null)
                connection.disconnect();
        }
    }

    public boolean deleteUser(String username){
        HttpURLConnection connection = null;
        try {
            URL url = new URL(prop.getUsersURL() + "/" + username);
            connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("DELETE");
            connection.setRequestProperty("Content-Type", "application/json");

            int responseCode = connection.getResponseCode();

            return responseCode == HttpURLConnection.HTTP_NO_CONTENT;
        } catch (IOException e) {
            throw new RuntimeException("Failed to delete user.", e);
        } finally {
            if (connection != null) {
                connection.disconnect();
            }
        }
    }

    public boolean changeUserStatus(String username, StatusRequest req){
    HttpURLConnection connection = null;

        try {
            URL url = new URL(prop.getUsersURL() + "/" + username);
            connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("PUT");
            connection.setRequestProperty("Content-Type", "application/json");
            connection.setDoOutput(true);

            PrintWriter out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(connection.getOutputStream())));
            out.write(gson.toJson(req));
            out.close();
            int responseCode = connection.getResponseCode();

            return responseCode == HttpURLConnection.HTTP_OK;

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
