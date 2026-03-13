package com.akash.webApp.Controller;


import org.springframework.web.bind.annotation.RestController;

import com.akash.webApp.Model.users.HelpRequest;
import com.akash.webApp.Model.users.UsersModel;
import com.akash.webApp.Service.HelpRequestService;
import com.akash.webApp.Service.MyUserDetailsService;

import java.nio.file.attribute.UserPrincipal;
import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;




@RestController
@RequestMapping("/resident")
public class ResidentController {

  
    @Autowired
    HelpRequestService helpRequestService;
    @Autowired
    MyUserDetailsService userDetailsService;

    

    @GetMapping("/requests")
    public List<HelpRequest> getHelpRequests( ) {
       // System.out.println(principal);
        UsersModel user = userDetailsService.getUser();
        System.out.println(user.getEmail());

        return helpRequestService.getRequestByUser(user.getId());
    }
    
    @PostMapping("/requests")
    public String helpRequest(@RequestBody String message) {
        //TODO: process POST request
        
        try{
            Integer userId = userDetailsService.getUser().getId();
            helpRequestService.addRequests(userId, message);
            return "Success";

        }catch(Exception e){
            return "failed to Request";
        }
    }
    


  
    
    
}
