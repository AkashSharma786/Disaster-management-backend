package com.akash.webApp.Controller;


import com.akash.webApp.Repository.UsersRepo;
import com.akash.webApp.Service.RescueService;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.simple.JdbcClient.ResultQuerySpec;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.akash.webApp.Model.DisasterReport;
import com.akash.webApp.Model.rescue.RescueTask;
import com.akash.webApp.Model.users.RoleEnum;
import com.akash.webApp.Model.users.UsersModel;
import com.akash.webApp.Service.DisasterReportService;
import com.akash.webApp.Service.MyUserDetailsService;
import com.akash.webApp.Service.RegistrationService;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;


@RestController

@RequestMapping("/respondent")
public class RespondentController {

    
    @Autowired
    RescueService rescueService;
    @Autowired
    DisasterReportService disasterReportService;
    @Autowired
    RegistrationService registrationService;
    @Autowired
    MyUserDetailsService userDetailsService;

    

     @PostMapping("/reports")
    public String setReports(@RequestBody DisasterReport report) {

            
           return disasterReportService.addReport(report);
           
    }

    @GetMapping("/tasks/{responderId}")
    public List<RescueTask> getRescueTask(@PathVariable Integer responderId) {
        return rescueService.getResponderTasks(responderId);
        
    }
    
    @GetMapping("/reports")
    public List<DisasterReport> getReports() {
       
            Integer responderId = userDetailsService.getUser().getId();
            System.out.println("responder id : " + responderId);
            
            return disasterReportService.getByResponder(responderId);
    }
    
    
    
}
