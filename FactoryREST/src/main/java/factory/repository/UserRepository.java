package factory.repository;

import com.google.gson.Gson;
import factory.model.User;

import java.io.*;

public class UserRepository {

    private Gson gson = new Gson();
    private String USERS_PATH;


    public UserRepository() {


    }


    public boolean add(User user) {

        try (PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(USERS_PATH, true)))) {

            out.println(gson.toJson(user));
        } catch (IOException e) {
            System.err.println("User not added!");
            return false;
        }

        return true;
    }

}
