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
@CrossOrigin(origins = "http://localhost:5173")

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
    public Mono<AltertResponse> getAlerts() throws Exception {

        return apiService.getApiAlerts();

    }

    @GetMapping("/ndma-alerts/{state_id}")
    public Mono<ResponseEntity<AltertResponse>> getStateAlerts(@PathVariable Integer state_id) throws Exception {
        if (state_id < 1 || state_id > 36)
            return Mono.just(ResponseEntity.badRequest().body(null));

        Mono<AltertResponse> result = apiService.getApiAlerts(state_id);

        return result.map(res -> {

            return ResponseEntity.ok(res);
        });

    }

    @GetMapping("/ndma-alerts/{state_id}/{item_index}")
    public Mono<AlertItem> getAlertItem(@PathVariable Integer state_id, @PathVariable Integer item_index)
            throws Exception {

        Mono<AlertItem> result = alertItemService.getAlertItem(state_id, item_index);

        return result;
    }

    @PostMapping("/ndma-alerts/{state_id}/{item_index}")
    public Mono<String> saveAlertItem(@PathVariable Integer state_id, @PathVariable Integer item_index)
            throws Exception {

        return alertItemService.saveAlertItem(state_id, item_index);

    }

    @GetMapping("/ndma-alerts/{state_id}/alerts")
    public Flux<AlertItem> getAlertItem(@PathVariable Integer state_id) throws Exception {

        Flux<AlertItem> result = alertItemService.getAlerts(state_id);

        return result;
    }

    @GetMapping("/users")
    public List<UsersModel> getAllUsers() {

        return registrationService.getAllUsers();
    }

    @GetMapping("/responders")
    public Flux<UsersModel> getResponders() {

        return registrationService.getResponders();
    }

    @GetMapping("/ndma-alerts/saved")
    public Flux<AlertItem> getSavedAlerts() {
        // TODO: process PUT request
        return alertItemService.getSavedAlertItems();

    }

    @GetMapping("/ndma-alerts/saved/{id}")
    public Mono<AlertItem> getSavedAlertById(@PathVariable Integer id) {
        // TODO: process PUT request
        return alertItemService.getSavedAlertItemById(id);

    }

    @PutMapping("/ndma-alerts/saved/{id}")
    public Mono<String> editSavedAlerts(@PathVariable Integer id, @RequestBody AlertItem updatedItem) {
        return alertItemService.editSavedAlertItem(id, updatedItem);
    }

    @DeleteMapping("/ndma-alerts/saved/{id}")
    public Mono<String> deleteSavedAlert(@PathVariable Integer id) {
        return alertItemService.deleteSavedAlertItem(id);
    }

    @PostMapping("/rescue/create")
    public Mono<String> createRescueTask(@RequestBody RescueTask entity) {
        // TODO: process POST request
        return rescueService.createRescueTask(entity);
    }

    @GetMapping("/rescue/tasks")
    public Flux<RescueTask> getAllRescueTask() {
        return rescueService.getAllRescueTasks();
    }

    @GetMapping("/rescue/{id}")
    public Mono<RescueTask> getRescueTaskById(@PathVariable Integer id) {
        return rescueService.getRescueTaskById(id);
    }

    @PutMapping("/rescue/update/{id}")
    public Mono<String> updateRescueTask(@PathVariable Integer id, @RequestBody RescueTask updatedTask) {
            return rescueService.updateRescueTask(id, updatedTask);
   
    }

    @DeleteMapping("/rescue/delete/{id}")
    public Mono<String> deleteRescueTask(@PathVariable Integer id) {
        return rescueService.deleteRescueTask(id);
    }

}
