package com.akash.webApp.Model.AlertModels;

import java.time.OffsetDateTime;
import java.util.List;

import org.hibernate.annotations.ManyToAny;
import org.springframework.stereotype.Component;

import com.akash.webApp.Model.District;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;

@Entity
public class AlertItem {
    @Id
    @GeneratedValue(strategy = jakarta.persistence.GenerationType.SEQUENCE)
    private Integer id;

    private String event;
    private String urgency;
    private String severity;
    private String certainty;
    @Lob
    private String message;
    private String instruction;
    private OffsetDateTime effectiveDate;
    private OffsetDateTime expiryDate;

    @ManyToAny
    private List<District> district;

    public OffsetDateTime getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(OffsetDateTime expiryDate) {
        this.expiryDate = expiryDate;
    }

    
    public AlertItem(){}

    public AlertItem(String event, String urgency, String severity, String certainty, String message,
            String instruction, OffsetDateTime effectiveDate, OffsetDateTime expiryDate, List<District> district) {
        this.event = event;
        this.urgency = urgency;
        this.severity = severity;
        this.certainty = certainty;
        this.message = message;
        this.instruction = instruction;
        this.effectiveDate = effectiveDate;
        this.expiryDate = expiryDate;
        this.district = district;
    }

    public String getSeverity() {
        return severity;
    }

    public void setSeverity(String severity) {
        this.severity = severity;
    }

    public String getCertainty() {
        return certainty;
    }

    public void setCertainty(String certainty) {
        this.certainty = certainty;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getInstruction() {
        return instruction;
    }

    public void setInstruction(String instruction) {
        this.instruction = instruction;
    }

    public OffsetDateTime getEffectiveDate() {
        return effectiveDate;
    }

    public void setEffectiveDate(OffsetDateTime effectiveDate) {
        this.effectiveDate = effectiveDate;
    }

 

    public List<District> getDistrict() {
        return district;
    }

    public void setDistrict(List<District> district) {
        this.district = district;
    }

    public String getUrgency() {
        return urgency;
    }

    public void setUrgency(String urgency) {
        this.urgency = urgency;
    }

    public void setEvent(String event) {
        this.event = event;
    }

    public String getEvent() {
        return event;
    }

}
