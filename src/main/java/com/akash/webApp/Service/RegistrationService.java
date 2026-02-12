package com.akash.webApp.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.akash.webApp.Model.RegistrationModel;
import com.akash.webApp.Repository.RegistrationRepo;

@Component
public class RegistrationService {

    @Autowired
    private RegistrationRepo registrationRepo;

    public String registerUser(String username, String email, String password, String location, Long pone, String role) {
        // Here you would typically save the user details to a database
        // For simplicity, we are just printing the details and returning a success message
        if(username == null || email == null || password == null || location == null || pone == null || role == null    ) {
            return "Invalid registration data";
        }

        RegistrationModel model = new RegistrationModel(username, email, password);
        model.setLocation(location);
        model.setPhoneNumber(pone);
        model.setRole(role);

        registrationRepo.save(model);

        System.out.println("Registering user: " + username + ", " + email);
        return "User registered successfully";
    }

    public List<RegistrationModel> getAllUsers() {
        return registrationRepo.findAll();
    }
    
}
