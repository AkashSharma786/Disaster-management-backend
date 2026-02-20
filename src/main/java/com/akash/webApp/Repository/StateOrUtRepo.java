package com.akash.webApp.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.akash.webApp.Model.StateOrUt;

@Repository
public interface StateOrUtRepo extends JpaRepository<StateOrUt, Integer>{

    
}
