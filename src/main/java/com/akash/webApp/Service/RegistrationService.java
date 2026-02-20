package com.akash.webApp.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import com.akash.webApp.Model.UsersModel;

import com.akash.webApp.Repository.UsersRepo;

@Component
public class RegistrationService {

    public BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder(12) ;

    @Autowired
    private UsersRepo usersRepo;

    public String registerUser(UsersModel user) {
        // Here you would typically save the user details to a database
        // For simplicity, we are just printing the details and returning a success message
        if(user.getFirstName() == null 
            || user.getLastName() == null
            || user.getEmail() == null 
            || user.getPassword() == null 
            || user.getDistrictLGDCode() == null
            || user.getPhoneNumber() == null 
            || user.getRole() == null    ) {
            return "Invalid registration data";
        }

        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));

       

        usersRepo.save(user);

        System.out.println("Registering user: " + user.getFirstName() + " "+ user.getLastName() + ", " + user.getEmail());
        return "User registered successfully";
    }

    public List<UsersModel> getAllUsers() {
        return usersRepo.findAll();
    }

}
