package com.akash.webApp.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.akash.webApp.Model.users.HelpRequest;
import com.akash.webApp.Model.users.UsersModel;

public interface HelpRequestRepo extends JpaRepository<HelpRequest, Integer> {

    List<HelpRequest> findByUser(UsersModel user);

    
} 
