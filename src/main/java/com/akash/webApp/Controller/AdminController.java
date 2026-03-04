package com.akash.webApp.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.akash.webApp.Model.DisasterReport;
import com.akash.webApp.Model.AlertModels.AlertHeading;
import com.akash.webApp.Model.AlertModels.AlertItem;
import com.akash.webApp.Model.AlertModels.AltertResponse;
import com.akash.webApp.Model.rescue.RescueStatusEnum;
import com.akash.webApp.Model.rescue.RescueTask;
import com.akash.webApp.Model.users.UsersModel;
import com.akash.webApp.Service.ApiService;
import com.akash.webApp.Service.DisasterReportService;
import com.akash.webApp.Service.RegistrationService;
import com.akash.webApp.Service.RescueService;
import com.akash.webApp.Service.AlertServices.AlertItemService;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;

@RestController

@RequestMapping("/admin")
public class AdminController {

    @Autowired
    DisasterReportService disasterReportService;
    @Autowired
    ApiService apiService;
    @Autowired
    AlertItemService alertItemService;

    @Autowired
    RescueService rescueService;

    @Autowired
    RegistrationService registrationService;

    @GetMapping("/reports")
    public List<DisasterReport> requestMethodName() {
        return disasterReportService.getAllReports();
    }

    @GetMapping("/ndma-alerts")
    public AltertResponse getAlerts() throws Exception {

        System.out.println("Fetching alerts from NDMA API...");
        // return "success";

        return apiService.getApiAlerts();

    }

    @GetMapping("/ndma-alerts/{state_id}")
    public ResponseEntity<AltertResponse> getStateAlerts(@PathVariable Integer state_id) throws Exception {
        if (state_id < 1 || state_id > 36)
            return ResponseEntity.badRequest().body(null);

        AltertResponse result = apiService.getApiAlerts(state_id);

        return ResponseEntity.ok(result);

    }

    @GetMapping("/ndma-alerts/{state_id}/{item_index}")
    public AlertItem getAlertItem(@PathVariable Integer state_id, @PathVariable Integer item_index)
            throws Exception {

        AlertItem result = alertItemService.getAlertItem(state_id, item_index);

        return result;
    }

    @PostMapping("/ndma-alerts/{state_id}/{item_index}")
    public String saveAlertItem(@PathVariable Integer state_id, @PathVariable Integer item_index)
            throws Exception {

        return alertItemService.saveAlertItem(state_id, item_index);

    }

    @GetMapping("/ndma-alerts/{state_id}/alerts")
    public List<AlertItem> getAlertItem(@PathVariable Integer state_id) throws Exception {

        List<AlertItem> result = alertItemService.getAlerts(state_id);

        return result;
    }

    @GetMapping("/users")
    public List<UsersModel> getAllUsers() {

        return registrationService.getAllUsers();
    }

    @GetMapping("/responders")
    public List<UsersModel> getResponders() {

        return registrationService.getResponders();
    }

    @GetMapping("/ndma-alerts/saved")
    public List<AlertItem> getSavedAlerts() {
        // TODO: process PUT request
        return alertItemService.getSavedAlertItems();

    }

    @GetMapping("/ndma-alerts/saved/{id}")
    public AlertItem getSavedAlertById(@PathVariable Integer id) {
        // TODO: process PUT request
        try {
            return alertItemService.getSavedAlertItemById(id);
        } catch (Exception e) {
            System.out.print(e.getMessage());
            return null;
        }

    }

    @PutMapping("/ndma-alerts/saved/{id}")
    public String editSavedAlerts(@PathVariable Integer id, @RequestBody AlertItem updatedItem) {
        try {
            return alertItemService.editSavedAlertItem(id, updatedItem);
        } catch (Exception e) {
            System.out.print(e.getMessage());
            return null;
        }
    }

    @DeleteMapping("/ndma-alerts/saved/{id}")
    public String deleteSavedAlert(@PathVariable Integer id) {
        return alertItemService.deleteSavedAlertItem(id);
    }

    @PostMapping("/rescue/create")
    public String createRescueTask(@RequestBody RescueTask entity) {
        // TODO: process POST request
        return rescueService.createRescueTask(entity);
    }

    @GetMapping("/rescue/tasks")
    public List<RescueTask> getAllRescueTask() {
        return rescueService.getAllRescueTasks();
    }

    @GetMapping("/rescue/{id}")
    public RescueTask getRescueTaskById(@PathVariable Integer id) {
        return rescueService.getRescueTaskById(id);
    }

    @PutMapping("/rescue/update/{id}")
    public String updateRescueTask(@PathVariable Integer id, @RequestBody RescueTask updatedTask) {
        return rescueService.updateRescueTask(id, updatedTask);

    }

    @DeleteMapping("/rescue/delete/{id}")
    public String deleteRescueTask(@PathVariable Integer id) {
        return rescueService.deleteRescueTask(id);
    }

}
