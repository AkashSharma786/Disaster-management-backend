package com.akash.webApp.Model.users;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity
public class HelpRequest {

    @Id
    @GeneratedValue(strategy =  GenerationType.SEQUENCE)
    private Integer id;

    @ManyToOne
    private UsersModel user;
    private String message;
    


    
    public HelpRequest() {
    
    }

    public HelpRequest(UsersModel user, String message) {
        this.user = user;
        this.message = message;
    }

    public int getId() {
        return id;
    }
    
    public UsersModel getUser() {
        return user;
    }
    public void setUser(UsersModel user) {
        this.user = user;
    }
    public String getMessage() {
        return message;
    }
    public void setMessage(String message) {
        this.message = message;
    }

    
}
