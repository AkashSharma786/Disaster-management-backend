package com.akash.webApp.Service;

import java.time.Duration;
import java.util.function.Consumer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.akash.webApp.Apis;

import reactor.core.publisher.Mono;

@Service
public class ApiService {
String res = "";
    private WebClient localClient;
     
   
    public  ApiService(){
        this.localClient = new Apis().getWeblient();
    }

    public String getApiAlerts() {
        Mono<String> response =
        localClient.get()
        .retrieve()
        .bodyToMono(String.class);

        
        
        
        String err;
        Consumer<String> dataConsumer = new Consumer<String>()  {
           public void accept(String data){
            //System.out.println(data);
            res += res.concat(data) ;
            System.out.print(res);
          }  
        };
          
        response.subscribe(dataConsumer);
        response.block( Duration.ofMillis(3000));

         return res;
    }

    
    

    
}
