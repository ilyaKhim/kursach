package ru.il.service;

import ru.il.model.User;
import ru.il.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User authenticateUser(String username, String password) {
        Optional<User> foundUser = userRepository.findByLogin(username);
        if (foundUser.isPresent() && password.equals(foundUser.get().getPassword())) {
            return foundUser.get();
        }
        return null;
    }
}
