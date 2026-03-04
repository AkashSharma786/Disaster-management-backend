package com.akash.webApp.Service;


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

    public String createRescueTask(RescueTask task) {
        
            
            rescueTaskRepo.save(task);
            return "Rescue task created successfully";
        
    }

    public RescueTask getRescueTaskById(Integer id) {
        Optional<RescueTask> task = rescueTaskRepo.findById(id);

        if(task.isPresent()) {
            return task.get();
        } else {
            return null;
        }
        
    }

    public List<RescueTask> getAllRescueTasks() {
        return rescueTaskRepo.findAll();

    }

    public String updateRescueTask(Integer id, RescueTask updatedTask) {
         
          RescueTask rescueTask =  getRescueTaskById(id);
          if(rescueTask == null)
              return "Failure to fin rescue task with id : "+ id;
          
            
            

                if(updatedTask.getAlertItem() != null) {
                    rescueTask.setAlertItem(updatedTask.getAlertItem());
                }
                if(updatedTask.getStatus() != null) {
                    rescueTask.setStatus(updatedTask.getStatus());
                }
                if(updatedTask.getVolunteers() != null) {
                    rescueTask.setVolunteers(updatedTask.getVolunteers());
                }
                if(updatedTask.getMessage() != null) {
                    rescueTask.setMessage(updatedTask.getMessage());
                }
                 rescueTaskRepo.save(rescueTask);
            
                return "Rescue task updated successfully";
           
                
            
          
    }

  
 

    public String deleteRescueTask(Integer id) {

        RescueTask rescueTask =  getRescueTaskById(id);
        if(rescueTask == null)
            return "Rescue task not found";

        
        rescueTaskRepo.delete(rescueTask);
            return "Rescue task deleted successfully";
   
    }


    
}
