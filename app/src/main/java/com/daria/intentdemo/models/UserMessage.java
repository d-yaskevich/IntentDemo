package com.daria.intentdemo.models;

import java.io.Serializable;

public class UserMessage implements Serializable {
    //fields
    private User user;
    private String message;

    //default constructor
    public UserMessage(User user, String message) {
        this.user = user;
        this.message = message;
    }

    //custom constructor
    public UserMessage(String name, int age, String message) {
        this.user = new User(name, age);
        this.message = message;
    }

    //getters

    public User getUser() {
        return user;
    }

    public String getMessage() {
        return message;
    }

    //setters

    public void setMessage(String message) {
        this.message = message;
    }
}
