package com.akash.webApp.Service.AlertServices;

import java.util.ArrayList;
import java.util.List;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.stereotype.Service;
import com.akash.webApp.Model.AlertModels.AlertHeading;
import com.akash.webApp.Model.AlertModels.AltertResponse;

import reactor.core.publisher.Mono;

@Service
public class AlertHeadingService {

    public Mono<AltertResponse> getAlertHeadings(Mono<Object> obj) {

      return  obj.map(object -> {
            JSONObject jsonObject = (JSONObject) object;
            JSONObject channel = (JSONObject) jsonObject.get("channel");

            

            Object itemObj =  channel.get("item");

            List<Object> items ;

            items = new ArrayList<>();
            if(itemObj == null){
                
            }
            else if((itemObj instanceof JSONObject))
            {   
                items.add(itemObj);
            }
            else{
                  items = (JSONArray)itemObj;
                }
               
           
            

            AltertResponse alertRes = new AltertResponse();
            List<AlertHeading> alertHeadings = new ArrayList<>(); 

            for (int i = 0; i < items.size(); i++) {
            JSONObject item = (JSONObject) items.get(i);

            AlertHeading alertHeading = new AlertHeading((String) item.get("title"), (String) item.get("link"),
                    (String) item.get("pubDate"));
            alertHeadings.add(alertHeading);
            // System.out.println(alertHeading.toString());
            

           

            } 

            alertRes.setAlertHeadings(alertHeadings);
            return alertRes;


        });



        

        
        

       

        
       

    }

}
