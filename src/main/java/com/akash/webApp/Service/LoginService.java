package com.akash.webApp.Service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.security.autoconfigure.SecurityProperties.User;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.akash.webApp.Model.LoginModel;
import com.akash.webApp.Model.users.UserPrincipal;
import com.akash.webApp.Model.users.UsersModel;


@Service
public class LoginService {

    @Autowired
    AuthenticationManager authManager;

    @Autowired
    JWTService jwtService;
    @Autowired
    MyUserDetailsService myUserDetailsService;
   
    
  

   public String verify(LoginModel loginBody){

    Authentication authentication 
    = authManager.authenticate(new UsernamePasswordAuthenticationToken(loginBody.getUsername(), loginBody.getPassword()));

    
    if(authentication.isAuthenticated()){
   
        UserPrincipal user =   myUserDetailsService.loadUserByUsername(loginBody.getUsername());
   
    
        

        return jwtService.generateToken(loginBody.getUsername(), user.getUser());

    }
    return "Authentication Failed";
   }
    
}
