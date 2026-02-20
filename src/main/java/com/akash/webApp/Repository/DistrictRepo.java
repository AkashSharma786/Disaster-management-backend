package com.akash.webApp.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.akash.webApp.Model.District;

@Repository
public interface DistrictRepo extends JpaRepository<District, Integer>{
  
} 
