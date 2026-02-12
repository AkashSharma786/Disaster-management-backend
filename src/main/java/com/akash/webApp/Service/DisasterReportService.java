package com.akash.webApp.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.akash.webApp.Model.DisasterReport;
import com.akash.webApp.Repository.DisasterReportRepo;

@Service
public class DisasterReportService {
    
    @Autowired
    private DisasterReportRepo disasterReportRepo;
    
    private List<DisasterReport> reports =  new ArrayList<>(Arrays.asList( new DisasterReport("Earthquake", "Kolkata", "High", "20-08-20025"),
             new DisasterReport("Flood", "Mumbai", "Medium", "15-09-2024"),
             new DisasterReport("Cyclone", "Chennai", "Low", "10-10-2024")));

    public List<DisasterReport> getAllReports() {
        
        return disasterReportRepo.findAll();
    }

    public String addReport(DisasterReport report) {
        // Logic to add the report to the database or in-memory list
        // For now, we just print the report details
        String disasterType = report.getDisasterType();
        String location = report.getLocation();
        String severity = report.getSeverity();
        String disasterDate = report.getDisasterDate();

        if(disasterType == null || location == null || severity == null || disasterDate == null) {
            return "Invalid report data";
        }

        reports.add(report);
        disasterReportRepo.save(report);

        

        System.out.println("Received report: " + disasterType + " at " + location + " with severity " + severity + " on " + disasterDate);
        return "Report received successfully";
        
    }
    
}
