package com.akash.webApp.Model.AlertModels;

import java.util.List;

import org.springframework.stereotype.Component;

import com.akash.webApp.Model.District;

public class AlertItem {
    private String event;
    private String urgency;
    private String severity;
    private String certainty;
    private String message;
    private String instruction;
    private String effectiveDate;
    private String expiryDate;

    
    public String getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(String expiryDate) {
        this.expiryDate = expiryDate;
    }

    private List<District> district;

    public AlertItem(String event, String urgency, String severity, String certainty, String message,
            String instruction, String effectiveDate, String expiryDate, List<District> district) {
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

    public String getEffectiveDate() {
        return effectiveDate;
    }

    public void setEffectiveDate(String effectiveDate) {
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
