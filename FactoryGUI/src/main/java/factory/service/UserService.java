package factory.service;

import com.google.gson.Gson;
import factory.model.User;
import factory.properties.UserProperties;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class UserService {

    private UserProperties prop = new UserProperties();
    private Gson gson = new Gson();
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
}
