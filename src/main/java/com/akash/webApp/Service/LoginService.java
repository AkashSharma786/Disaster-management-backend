package com.akash.webApp.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import com.akash.webApp.Model.LoginModel;


@Service
public class LoginService {

    @Autowired
    AuthenticationManager authManager;

    @Autowired
    JWTService jwtService;
    
  

   public String verify(LoginModel loginBody){

    Authentication authentication 
    = authManager.authenticate(new UsernamePasswordAuthenticationToken(loginBody.getUsername(), loginBody.getPassword()));
    if(authentication.isAuthenticated())
        return jwtService.generateToken(loginBody.getUsername());
    return "Authentication Failed";
   }
    
}
