package com.akash.webApp.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

import com.akash.webApp.Model.UsersModel;
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


    @PostMapping("/api/auth/register")
    public String postMethodName(@RequestBody UsersModel entity) {
        return RegistrationService.registerUser(entity);
    }
    
       
    
    
}
