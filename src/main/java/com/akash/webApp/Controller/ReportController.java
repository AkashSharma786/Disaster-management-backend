package com.akash.webApp.Controller;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.web.bind.annotation.RequestBody;
import com.akash.webApp.Service.DisasterReportService;

import jakarta.servlet.http.HttpServletRequest;

import com.akash.webApp.Model.DisasterReport;
import java.util.List;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
@CrossOrigin(origins = "http://localhost:5173")
public class ReportController {

 
    @Autowired
    private DisasterReportService disasterReportService;
   

   

   

   @GetMapping("/")
   public String greet(HttpServletRequest request) {

     
   
        
       return new String("Hello" );
   }
   
    @GetMapping("/admin/reports")
    public List<DisasterReport> requestMethodName() {
        return disasterReportService.getAllReports();
    }

    @PostMapping("/admin/reports")
    public String postMethodName(@RequestBody DisasterReport entity) {
           String result = disasterReportService.addReport(entity);
          
           return "report Created";
    }
    
    
   
    
    
    
}
