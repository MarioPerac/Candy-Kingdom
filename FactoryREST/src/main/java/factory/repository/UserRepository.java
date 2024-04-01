package factory.repository;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import factory.model.User;
import factory.properties.UserProperties;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;

public class UserRepository {

    private final Gson gson = new Gson();
    UserProperties prop = new UserProperties();


    public boolean add(User user) {
        List<User> users = getAll();
        users.add(user);

        try (FileWriter out = new FileWriter(prop.getUsersPath())) {
            out.write(gson.toJson(users));
        } catch (IOException e) {

            System.err.println("User not added!");
            return false;
        }

        return true;
    }

    public List<User> getAll() {

        User[] users = null;
        try (BufferedReader in = new BufferedReader(new FileReader(prop.getUsersPath()))) {
            StringBuilder jsonContent = new StringBuilder();
            String line;
            while ((line = in.readLine()) != null) {
                jsonContent.append(line);
            }

            users = gson.fromJson(jsonContent.toString(), User[].class);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }


        if (users == null) {
            return new ArrayList<>();
        }

        return new ArrayList<>(Arrays.asList(users));
    }

}
