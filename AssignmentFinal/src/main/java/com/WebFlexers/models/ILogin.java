package com.WebFlexers.models;

public interface ILogin {
    boolean validatePassword(String password);
    User login(String username, String password);
}
