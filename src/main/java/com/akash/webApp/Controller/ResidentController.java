package com.akash.webApp.Controller;


import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;


@RestController
@CrossOrigin(origins = "http://localhost:5173")
public class ResidentController {

    @GetMapping("/resident/alerts")
    public String getMethodName() {
        return new String("Disaster alerts");
    }
    
    
}
