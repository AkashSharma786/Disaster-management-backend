package com.akash.webApp.Model;

import org.springframework.stereotype.Component;

import jakarta.persistence.Entity;

@Component
public class LoginModel {
    private String username;
    private String password;

    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return this.password;

    }

    public void setPassword(String password) {
        this.password = password;
    }


    @Override
    public String toString() {
        // TODO Auto-generated method stub
        return this.username + " -> p " + this.password;
    }

}
