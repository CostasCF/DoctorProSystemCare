package com.WebFlexers.models;

public class User {

    protected String username;
    protected String password;
    protected String firstName;
    protected String surname;
    private static int usersCounter = 0;

    public User() {}

    // Constructor
    public User(String username, String password, String firstname, String surname) {
        this.username = username;
        this.password = password;
        this.firstName = firstname;
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

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
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
     * User logout
     */
    public void logout() {

    }
}
