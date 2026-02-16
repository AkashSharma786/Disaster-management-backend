package com.akash.webApp.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.akash.webApp.Model.UsersModel;
import java.util.List;


public interface UsersRepo extends JpaRepository<UsersModel, Integer> {

     UsersModel findByEmail(String email);
     UsersModel  findByPhoneNumber(Long phoneNumber);

    
}
