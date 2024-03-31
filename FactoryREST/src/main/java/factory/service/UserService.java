package factory.service;

import factory.model.Status;
import factory.model.User;
import factory.repository.UserRepository;

public class UserService {

    UserRepository userRepository = new UserRepository();

    public boolean registerUser(User user) {
        user.setStatus(Status.PENDING.toString());
        return userRepository.add(user);
    }
}
