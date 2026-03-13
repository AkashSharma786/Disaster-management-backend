package com.akash.webApp.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.akash.webApp.Model.DisasterReport;
import com.akash.webApp.Model.users.UsersModel;
import com.akash.webApp.Model.rescue.RescueTask;



@Repository
public interface DisasterReportRepo extends JpaRepository<DisasterReport, Integer> {

    List<DisasterReport> findByResponder(UsersModel responder);
    List<DisasterReport> findByRescueTask(RescueTask rescueTask);

} 
