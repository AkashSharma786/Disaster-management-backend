package com.akash.webApp.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.akash.webApp.Model.rescue.RescueTask;

public interface RescueTaskRepo extends JpaRepository<RescueTask, Integer> {

    
} 