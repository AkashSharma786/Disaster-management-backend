package com.akash.webApp.Controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.akash.webApp.Model.DisasterReport;
import com.akash.webApp.Service.DisasterReportService;

@RestController
@CrossOrigin(origins = "http://localhost:5173")
public class RespondentController {

    @Autowired
    DisasterReportService disasterReportService;

     @PostMapping("/respondent/reports")
    public String postMethodName(@RequestBody DisasterReport entity) {
           String result = disasterReportService.addReport(entity);
          
           return "report Created";
    }
    
}
