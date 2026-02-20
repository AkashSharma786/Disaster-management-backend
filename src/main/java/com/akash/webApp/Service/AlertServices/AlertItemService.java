package com.akash.webApp.Service.AlertServices;

import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.akash.webApp.Model.AlertModels.AlertHeading;
import com.akash.webApp.Model.AlertModels.AlertItem;
import com.akash.webApp.Model.AlertModels.AltertResponse;
import com.akash.webApp.Service.ApiService;

@Service
public class AlertItemService {

    private AlertItem alertItem;
    @Autowired
    private ApiService apiService ;

    public String getAlertItem(Integer index) {

        

        // try{

        //     AltertResponse response =  apiService.getApiAlerts();
        //     System.out.println(response);
        //     AlertHeading  alertHeading = response.getAlertHeadings().get(0);
        //     System.out.println(alertHeading);

            

        
            
        //     String link =  alertHeading.getLink();
        //     System.out.println(link);

        //     WebClient client = WebClient.create(link);

        //     Object obj = apiService.loadResponse(client);

        //    System.out.println(obj.toString());

        //     // JSONObject jsonObject = (JSONObject) obj;
        //     // System.out.println(jsonObject.toString());
        //     // JSONObject alert = (JSONObject) jsonObject.get("cap:alert");
        //     // JSONArray items = (JSONArray) alert.get("cap:info");
        //     // System.out.println(items.toJSONString());



        //     return "sucess";
            
        // }
        // catch(Exception e){
        //     System.out.println(e);
        //     return "failure";
        // }
        return "success";

    }
    
}
