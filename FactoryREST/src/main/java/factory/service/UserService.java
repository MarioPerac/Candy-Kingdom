package factory.service;

import factory.model.Status;
import factory.model.User;
import factory.model.UserInfo;
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
}
