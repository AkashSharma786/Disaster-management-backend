package com.akash.webApp.Model.rescue;

import java.util.List;

import com.akash.webApp.Model.AlertModels.AlertItem;
import com.akash.webApp.Model.users.UsersModel;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;

@Entity
public class RescueTask {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;


    
    @ManyToOne
    private AlertItem alertItem;

    
    @ManyToMany
    private List<UsersModel> volunteers;

    @Column(nullable = false)
    private RescueStatusEnum status;

    private String message;

    

    public RescueTask(AlertItem alertItem, List<UsersModel> volunteers, RescueStatusEnum status, String message) {
        this.alertItem = alertItem;
        this.volunteers = volunteers;
        this.status = status;
        this.message = message;
    }


    public RescueTask() {
    }
    

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public AlertItem getAlertItem() {
        return alertItem;
    }

    public void setAlertItem(AlertItem alertItem) {
        this.alertItem = alertItem;
    }

    public List<UsersModel> getVolunteers() {
        return volunteers;
    }

    public void setVolunteers(List<UsersModel> volunteers) {
        this.volunteers = volunteers;
    }

    public RescueStatusEnum getStatus() {
        return status;
    }

    public void setStatus(RescueStatusEnum status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}
