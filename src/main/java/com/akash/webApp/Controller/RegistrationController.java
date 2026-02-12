package com.akash.webApp.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

import com.akash.webApp.Model.RegistrationModel;
import com.akash.webApp.Service.RegistrationService;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import java.util.List;


@RestController
@CrossOrigin(origins = "http://localhost:5173")
public class RegistrationController {

    @Autowired
    private RegistrationService RegistrationService;

    

    @GetMapping("/register")
    public List<RegistrationModel> getAllUsers() {

        return RegistrationService.getAllUsers();
    }


    @PostMapping("/register")
    public String postMethodName(@RequestBody RegistrationModel entity) {
        //TODO: process POST request
        
        return RegistrationService.registerUser(
            entity.getUsername(),
            entity.getEmail(),
            entity.getPassword(),
            entity.getLocation(),
            entity.getPhoneNumber(),
            entity.getRole()
        );
    }
    
       
    
    
}
