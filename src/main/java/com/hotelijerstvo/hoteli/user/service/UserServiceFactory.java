package com.hotelijerstvo.hoteli.user.service;

public enum UserServiceFactory {
    USER_SERVICE(new UserService());

    private UserServiceLocal userServiceLocal;

    UserServiceFactory(UserServiceLocal userServiceLocal){
        this.userServiceLocal = userServiceLocal;
    }

    public UserServiceLocal get() {
        return userServiceLocal;
    }
}
