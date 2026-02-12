package com.akash.webApp.Controller;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import com.akash.webApp.Service.DisasterReportService;
import com.akash.webApp.Model.DisasterReport;
import java.util.List;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import com.akash.webApp.Model.ApiResponse;





@RestController
@CrossOrigin(origins = "http://localhost:5173")
public class ReportController {
    @Autowired
    private DisasterReportService disasterReportService;

   
    @GetMapping("/reports")
    public List<DisasterReport> requestMethodName() {
        return disasterReportService.getAllReports();
    }

    @PostMapping("/reports")
    public ResponseEntity<ApiResponse> postMethodName(@RequestBody DisasterReport entity) {
           String result = disasterReportService.addReport(entity);
           ApiResponse response = new ApiResponse("Report added successfully",  result);
           return ResponseEntity.ok(response);
    }
    
    
   
    
    
    
}
