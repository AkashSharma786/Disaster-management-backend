package com.akash.webApp.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.akash.webApp.Model.rescue.RescueTask;
import com.akash.webApp.Model.users.UsersModel;

public interface RescueTaskRepo extends JpaRepository<RescueTask, Integer> {
    @Query("FROM RescueTask")
    List<RescueTask> findByResponder(UsersModel responder);
    
} 