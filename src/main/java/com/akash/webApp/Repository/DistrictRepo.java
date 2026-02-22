package com.akash.webApp.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.akash.webApp.Model.District;
import com.akash.webApp.Model.StateorUt;

import java.util.List;


@Repository
public interface DistrictRepo extends JpaRepository<District, Integer>{

    List<District> findByStateorUt(StateorUt state);
  
} 
