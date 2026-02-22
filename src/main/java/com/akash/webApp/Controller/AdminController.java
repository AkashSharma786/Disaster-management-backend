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
import com.akash.webApp.Model.AlertModels.AlertItem;
import com.akash.webApp.Model.AlertModels.AltertResponse;
import com.akash.webApp.Service.ApiService;
import com.akash.webApp.Service.DisasterReportService;
import com.akash.webApp.Service.RegistrationService;
import com.akash.webApp.Service.AlertServices.AlertItemService;

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
    AlertItemService alertItemService;

    @Autowired 
    RegistrationService registrationService;

    @GetMapping("/admin/reports")
    public List<DisasterReport> requestMethodName() {
        return disasterReportService.getAllReports();
    }

    @GetMapping("/admin/ndma-alerts")
    public Mono<AltertResponse> getAlerts()  throws Exception {
        
             return apiService.getApiAlerts();
             
        
    }

@GetMapping("/admin/ndma-alerts/{state_id}")
public Mono<ResponseEntity<AltertResponse>> getStateAlerts(@PathVariable Integer state_id) throws Exception {
    if(state_id < 1 || state_id > 36)
        return Mono.just(ResponseEntity.badRequest().body(null));

    Mono<AltertResponse> result = apiService.getApiAlerts(state_id);

    return result.map(res -> {
        
        return ResponseEntity.ok(res);
    });
    
}

@GetMapping("/admin/ndma-alerts/{state_id}/{item_index}")
public Mono<AlertItem> getAlertItem(@PathVariable Integer state_id, @PathVariable Integer item_index ) throws Exception {

        Mono<AlertItem> result = alertItemService.getAlertItem(state_id, item_index);

        if(item_index < 0 || item_index > 20)
            return null;

        result.onErrorReturn(new AlertItem());

    
    return result;
}

    

    @GetMapping("/admin/users")
    public List<UsersModel> getAllUsers() {
        
        return registrationService.getAllUsers();
    }

    
}
