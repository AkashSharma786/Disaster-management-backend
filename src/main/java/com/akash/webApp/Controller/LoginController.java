package com.akash.webApp.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.akash.webApp.Model.LoginModel;
import com.akash.webApp.Model.UsersModel;
import com.akash.webApp.Service.LoginService;

import org.springframework.web.bind.annotation.PostMapping;


@RestController
@CrossOrigin(origins = "http://localhost:5173")
public class LoginController {


   @Autowired
   LoginService loginService;


    @PostMapping("/api/auth/login")
    public String postMethodName(@RequestBody LoginModel  loginBody) {
        
        return loginService.verify(loginBody);
    }
    

    
}
