package ru.il.service;

import ru.il.model.User;

public interface UserService {
    User authenticateUser(String username, String password);
}
