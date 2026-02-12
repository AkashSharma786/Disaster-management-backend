package com.akash.webApp.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.akash.webApp.Model.RegistrationModel;

public interface RegistrationRepo extends JpaRepository<RegistrationModel, Integer> {

    
}
