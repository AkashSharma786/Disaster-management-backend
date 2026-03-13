package com.akash.webApp.Model;

import java.util.Date;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.ManyToAny;
import org.springframework.stereotype.Component;

import com.akash.webApp.Model.AlertModels.AlertItem;
import com.akash.webApp.Model.rescue.RescueTask;
import com.akash.webApp.Model.users.UsersModel;

import jakarta.annotation.Generated;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;

import java.time.LocalDateTime;
import java.util.*;

@Entity
public class DisasterReport {
    @Id
    @GeneratedValue(strategy = jakarta.persistence.GenerationType.SEQUENCE)
    @Column(name = "id", nullable = false)
    private int id;

    @ManyToOne
    private  RescueTask rescueTask;


    public RescueTask getRescueTask() {
        return rescueTask;
    }


    public void setRescueTask(RescueTask rescueTask) {
        this.rescueTask = rescueTask;
    }

    private String message;

    @ManyToOne
    private UsersModel responder;

    @CreationTimestamp
    private LocalDateTime reportDate;
    
    public DisasterReport(RescueTask task, String message, UsersModel responder) {
        this.rescueTask = task;
        this.message = message;
        this.responder = responder;
       
    }


    public DisasterReport() {
    }
    

    public int getId() {
        return id;
    }
    
   

   

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public UsersModel getResponder() {
        return responder;
    }

    public void setResponder(UsersModel responder) {
        this.responder = responder;
    }

    public LocalDateTime getReportDate() {
        return reportDate;
    }

    public void setReportDate(LocalDateTime reportDate) {
        this.reportDate = reportDate;
    }
    

   
   




    
}
