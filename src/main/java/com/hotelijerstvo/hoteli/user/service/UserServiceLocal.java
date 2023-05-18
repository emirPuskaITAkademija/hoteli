package com.hotelijerstvo.hoteli.user.service;

import com.hotelijerstvo.hoteli.user.User;

import java.util.List;

public interface UserServiceLocal {


    List<User> findAll();

    User login(String username, String password);
}
