package com.WebFlexers.mainpackage;

public class Users {

    private String username;
    private String password;
    private String name;
    private String surname;
    private static int usersCounter = 0;

    // Constructor
    public Users(String username, String password, String name, String surname) {
        this.username = username;
        this.password = password;
        this.name = name;
        this.surname = surname;

        usersCounter += 1;
    }

    // Getters and Setters
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public static int getUsersCounter() {
        return usersCounter;
    }

    // Functionality methods

    /**
     * User login
     */
    public void login() { }
    /**
     * User logout
     */
    public void logout() { }
}
