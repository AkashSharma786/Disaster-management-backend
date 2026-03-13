package com.akash.webApp.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.akash.webApp.Model.DisasterReport;
import com.akash.webApp.Model.AlertModels.AlertItem;
import com.akash.webApp.Model.rescue.RescueTask;
import com.akash.webApp.Model.users.RoleEnum;
import com.akash.webApp.Model.users.UsersModel;
import com.akash.webApp.Repository.DisasterReportRepo;
import com.akash.webApp.Repository.RescueTaskRepo;
import com.akash.webApp.Repository.UsersRepo;

@Service
public class DisasterReportService {
    
    @Autowired
    private DisasterReportRepo disasterReportRepo;

    @Autowired
    private RescueTaskRepo rescueTaskRepo;
    @Autowired
    private UsersRepo usersRepo;
    


    public List<DisasterReport> getAllReports() {
        
        return disasterReportRepo.findAll();
    }

    public String addReport(DisasterReport report) {
        // Logic to add the report to the database or in-memory list
        // For now, we just print the report details
        UsersModel responder = report.getResponder();
        String message = report.getMessage();
        RescueTask task = report.getRescueTask();
        System.out.println(responder.toString());
        System.out.println(message);
       System.out.println(task);
        

        if(responder == null || message == null || task == null ) {

            return "Invalid report data";
        } 

        disasterReportRepo.save(report);

        return "Report received successfully";
        
    }
  
    public void deleteReport(Integer id){
        Optional<DisasterReport> report = disasterReportRepo.findById(id);
        if(report.isEmpty())
            return;
        disasterReportRepo.delete(report.get());
    }

    public List<DisasterReport> getByResqueTask(Integer resqueTaskId){
        Optional<RescueTask> task = rescueTaskRepo.findById(resqueTaskId);

        if(task.isEmpty())
            new ArrayList<DisasterReport>();
        
        return disasterReportRepo.findByRescueTask(task.get());
        

    }

    public List<DisasterReport> getByResponder(Integer responderId){
        Optional<UsersModel> responder = usersRepo.findById(responderId);

        if(responder.isEmpty() || responder.get().getRole().getName() != RoleEnum.RESPONDENT)
            return new ArrayList<>();

        return disasterReportRepo.findByResponder(responder.get());
    }

}
