package com.hotelijerstvo.hoteli.user.service;

import com.hotelijerstvo.hoteli.user.User;

import java.util.List;

public interface UserServiceLocal {

    UserServiceLocal USER_SERVICE = new UserService();
    List<User> findAll();

    User login(String username, String password);

    void save(User user);
}
