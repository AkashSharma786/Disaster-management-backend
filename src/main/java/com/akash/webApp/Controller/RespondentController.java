package com.akash.webApp.Controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.akash.webApp.Model.DisasterReport;
import com.akash.webApp.Service.DisasterReportService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


@RestController

@RequestMapping("/respondent")
public class RespondentController {

    @Autowired
    DisasterReportService disasterReportService;

     @PostMapping("/reports")
    public String setReports(@RequestBody DisasterReport entity) {
           String result = disasterReportService.addReport(entity);
           return "report Created";
    }

    @GetMapping("/tasks")
    public String getMethodName() {
        return new String();
    }
    
    @GetMapping("/reports")
    public String getReports() {
        return new String();
    }
    
    
    
}
