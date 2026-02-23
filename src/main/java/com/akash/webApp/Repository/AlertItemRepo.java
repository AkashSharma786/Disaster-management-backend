package com.akash.webApp.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.akash.webApp.Model.AlertModels.AlertItem;

public interface AlertItemRepo extends JpaRepository<AlertItem, Integer>{
    
}
