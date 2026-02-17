package com.akash.webApp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;


@Component
public class Apis {

    public WebClient  getWeblient(){
       
        return  WebClient.create("https://sachet.ndma.gov.in/cap_public_website/rss/rss_india.xml");
    }
    
}
