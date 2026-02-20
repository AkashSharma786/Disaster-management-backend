package com.akash.webApp.Model.AlertModels;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


@JsonIgnoreProperties(ignoreUnknown = true)
public class AltertResponse {

    private List<AlertHeading> item;

    public List<AlertHeading> getAlertHeadings(){
        return this.item;
    }

    public void setAlertHeadings(List<AlertHeading> item){
        this.item = item;
    }
    
}
