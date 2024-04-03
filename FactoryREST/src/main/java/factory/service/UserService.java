package factory.service;

import factory.model.*;
import factory.repository.UserRepository;

import java.util.List;

public class UserService {

    UserRepository userRepository = new UserRepository();

    public boolean registerUser(User user) {
        user.setStatus(Status.PENDING.toString());
        return userRepository.add(user);
    }

    public List<UserInfo> getUsers() {
        return userRepository.getAllUsersInfo();
    }

    public boolean changeUserStatus(String username, String status) {
        return userRepository.changeStatus(username, status);
    }

    public boolean deleteUser(String username) {
        return userRepository.delete(username);
    }

    public Login userAuthentication(String username, String password) {
        return userRepository.authentication(username, password);
    }

    public String getUserEmail(String username) {
        return userRepository.getUserEmail(username);
    }
}
