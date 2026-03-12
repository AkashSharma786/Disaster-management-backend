package com.akash.webApp.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.akash.webApp.Model.DisasterReport;
import com.akash.webApp.Model.AlertModels.AlertItem;
import com.akash.webApp.Model.rescue.RescueTask;
import com.akash.webApp.Model.users.UsersModel;
import com.akash.webApp.Repository.DisasterReportRepo;

@Service
public class DisasterReportService {
    
    @Autowired
    private DisasterReportRepo disasterReportRepo;
    


    public List<DisasterReport> getAllReports() {
        
        return disasterReportRepo.findAll();
    }

    public String addReport(DisasterReport report) {
        // Logic to add the report to the database or in-memory list
        // For now, we just print the report details
        UsersModel responder = report.getResponder();
        String message = report.getMessage();
        RescueTask task = report.getAlertItem();
        

        if(responder == null || message == null || task == null ) {
            return "Invalid report data";
        } 

        disasterReportRepo.save(report);

        return "Report received successfully";
        
    }
    
}
