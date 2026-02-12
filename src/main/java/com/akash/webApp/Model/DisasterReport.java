package com.akash.webApp.Model;

import java.util.Date;

import org.springframework.stereotype.Component;

import jakarta.annotation.Generated;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

import java.util.*;

@Entity
public class DisasterReport {
    @Id
    @GeneratedValue(strategy = jakarta.persistence.GenerationType.SEQUENCE)
    @Column(name = "id", nullable = false)
    private int id;
    private String disasterType;
    private String location;
    private String severity;
    private String disasterDate;

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getDisasterType() {
        return disasterType;
    }
    public void setDisasterType(String disasterType) {
        this.disasterType = disasterType;
    }
    public String getLocation() {
        return location;
    }
    public void setLocation(String location) {
        this.location = location;
    }
    public String getSeverity() {
        return severity;
    }
    public void setSeverity(String severity) {
        this.severity = severity;
    }
    public String getDisasterDate() {
        return disasterDate;
    }
    public void setDisasterDate(String disasterDate) {
        this.disasterDate = disasterDate;
    }

    public DisasterReport(String disasterType, String location, String severity, String disasterDate) {
        this.disasterType = disasterType;
        this.location = location;
        this.severity = severity;
        this.disasterDate = disasterDate;
    }

    public DisasterReport() {
    }




    
}
