package com.akash.webApp.Service;

import java.lang.foreign.Linker.Option;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.akash.webApp.Model.rescue.RescueStatusEnum;
import com.akash.webApp.Model.rescue.RescueTask;
import com.akash.webApp.Model.users.UsersModel;
import com.akash.webApp.Repository.RescueTaskRepo;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class RescueService {

    @Autowired
    private RescueTaskRepo rescueTaskRepo;

    public Mono<String> createRescueTask(RescueTask task) {
        return Mono.fromCallable(() -> {
            
            rescueTaskRepo.save(task);
            return "Rescue task created successfully";
        });
    }

    public Mono<RescueTask> getRescueTaskById(Integer id) {
        Optional<RescueTask> task = rescueTaskRepo.findById(id);

        if(task.isPresent()) {
            return Mono.just(task.get());
        } else {
            return Mono.empty();
        }
        
    }

    public Flux<RescueTask> getAllRescueTasks() {
        List<RescueTask> tasks = rescueTaskRepo.findAll();
        return Flux.fromIterable(tasks);
    }

    public Mono<String> updateRescueTask(Integer id, RescueTask updatedTask) {
        return 
            getRescueTaskById(id).map(existingTask -> {
                if(updatedTask.getAlertItem() != null) {
                    existingTask.setAlertItem(updatedTask.getAlertItem());
                }
                if(updatedTask.getStatus() != null) {
                    existingTask.setStatus(updatedTask.getStatus());
                }
                if(updatedTask.getVolunteers() != null) {
                    existingTask.setVolunteers(updatedTask.getVolunteers());
                }
                if(updatedTask.getMessage() != null) {
                    existingTask.setMessage(updatedTask.getMessage());
                }
                 rescueTaskRepo.save(existingTask);
            
                return "Rescue task updated successfully";
            })
            .onErrorReturn("Rescue task not found");
                
            
          
    }

  
 

    public Mono<String> deleteRescueTask(Integer id) {
       return getRescueTaskById(id).map(existingTask -> {
            rescueTaskRepo.delete(existingTask);
            return "Rescue task deleted successfully";
        }).onErrorReturn("Rescue task not found");
    }


    
}
