package com.hotelijerstvo.hoteli.user.service;

import com.hotelijerstvo.hoteli.service.BaseService;
import com.hotelijerstvo.hoteli.user.User;

public interface UserServiceLocal extends BaseService<User, Integer> {

    UserServiceLocal USER_SERVICE = new UserService();

    User login(String username, String password);
}
