package net.javaguides.springboot.service;

import net.javaguides.springboot.model.User;

public interface UserService {
    User authenticateUser(String username, String password);
}
