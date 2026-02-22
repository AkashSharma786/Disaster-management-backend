package com.akash.webApp.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.akash.webApp.Model.StateorUt;


@Repository
public interface StateorUtRepo extends JpaRepository<StateorUt, Integer>{

    
}
