package com.akash.webApp.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.akash.webApp.Model.DisasterReport;
import com.akash.webApp.Model.UsersModel;
import com.akash.webApp.Model.AlertModels.AltertResponse;
import com.akash.webApp.Service.ApiService;
import com.akash.webApp.Service.DisasterReportService;
import com.akash.webApp.Service.RegistrationService;

import reactor.core.publisher.Mono;

import org.springframework.web.bind.annotation.RequestParam;


@RestController
@CrossOrigin(origins = "http://localhost:5173")
public class AdminController {

    @Autowired
    DisasterReportService disasterReportService;
    @Autowired
    ApiService apiService;

    @Autowired 
    RegistrationService registrationService;

    @GetMapping("/admin/reports")
    public List<DisasterReport> requestMethodName() {
        return disasterReportService.getAllReports();
    }

    @GetMapping("/admin/ndma-alerts")
    public AltertResponse getMethodName()  throws Exception {
        
            AltertResponse  result =  apiService.getApiAlerts();
            return result;
        
    }

@GetMapping("/admin/ndma-alerts/{state_id}")
public ResponseEntity<AltertResponse> getMethodName(@PathVariable Integer state_id) throws Exception {
    if(state_id < 1 || state_id > 36)
        return ResponseEntity.badRequest().body(null);

    AltertResponse result = apiService.getApiAlerts(state_id);
    return ResponseEntity.ok(result);
}

    

    @GetMapping("/admin/users")
    public List<UsersModel> getAllUsers() {
        
        return registrationService.getAllUsers();
    }

    
}
