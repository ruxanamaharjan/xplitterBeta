package com.example.x_splitter;

public class UsersInfo {
    String email, username;

    public UsersInfo() {
        //empty constructor for reading value
    }

    public UsersInfo(String email, String username) {
        this.email = email;
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
