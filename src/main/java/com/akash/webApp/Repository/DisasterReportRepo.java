package com.akash.webApp.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.akash.webApp.Model.DisasterReport;

@Repository
public interface DisasterReportRepo extends JpaRepository<DisasterReport, Integer> {


    
} 
