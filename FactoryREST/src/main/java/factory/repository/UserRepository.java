package factory.repository;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import factory.model.User;
import factory.model.UserInfo;
import factory.properties.UserProperties;

import java.io.*;
import java.util.*;

public class UserRepository {

    private final Gson gson = new Gson();
    UserProperties prop = new UserProperties();


    public boolean add(User user) {
        List<User> users = getAll();
        users.add(user);
        return writeUsers(users);
    }

    private boolean writeUsers(List<User> users) {
        try (FileWriter out = new FileWriter(prop.getUsersPath())) {
            out.write(gson.toJson(users));
        } catch (IOException e) {

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

    public List<UserInfo> getAllUsersInfo() {

        UserInfo[] users = null;
        try (BufferedReader in = new BufferedReader(new FileReader(prop.getUsersPath()))) {
            StringBuilder jsonContent = new StringBuilder();
            String line;
            while ((line = in.readLine()) != null) {
                jsonContent.append(line);
            }

            users = gson.fromJson(jsonContent.toString(), UserInfo[].class);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }


        if (users == null) {
            return new ArrayList<>();
        }

        return new ArrayList<>(Arrays.asList(users));
    }

    public boolean changeStatus(String username, String status) {
        List<User> users = getAll();

        for (User u : users) {
            if (u.getUsername().equals(username))
                u.setStatus(status);
        }

        return writeUsers(users);
    }

    public boolean delete(String username) {
        List<User> users = getAll();

        int index = -1;
        for (int i = 0; i < users.size(); i++) {
            User u = users.get(i);
            if (u.getUsername().equals(username)) {
                index = i;
                break;
            }
        }

        if (index != -1) {

            users.remove(index);
            return writeUsers(users);
        }

        return false;
    }

    public boolean authentication(String username, String password) {

        List<User> users = getAll();

        for (User u : users) {
            if (u.getUsername().equals(username)) {
                return u.getPassword().equals(password);
            }
        }

        return false;
    }
}
