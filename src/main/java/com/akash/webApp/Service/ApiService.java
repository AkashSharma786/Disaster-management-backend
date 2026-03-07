package com.akash.webApp.Service;


import java.util.Arrays;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import com.akash.webApp.Apis;
import com.akash.webApp.Model.AlertModels.AltertResponse;
import com.akash.webApp.Service.AlertServices.AlertHeadingService;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import org.springframework.http.MediaType;

@Service
public class ApiService {
    String res = "";
    @Autowired
    AlertHeadingService alertHeadingService;
    RestTemplate restTemplate = new RestTemplate();
    

    private XmlMapper xmlMapper = new XmlMapper();

   

    public JSONObject getResponse(String link){
         
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_XML));
        HttpEntity <String> entity = new HttpEntity<String>(headers);

        String response = restTemplate.exchange(link, HttpMethod.GET, entity, String.class).getBody();

        try{
            JsonNode jsonNode = xmlMapper.readTree(response);

        Object parsed = JSONValue.parse(jsonNode.toString());
        if (!(parsed instanceof JSONObject)) {
            throw new IllegalStateException("Expected JSONObject but got " + parsed);
        }
        return (JSONObject) parsed;
        }
        catch(Exception e){
            System.out.println("Error Occurred");
            return null;
        }
        
        

    }

    public AltertResponse getApiAlerts() {
        String link = Apis.getLink();
        JSONObject response =  getResponse(link);
       
        System.out.println("Fetching alerts from NDMA API...");
        return alertHeadingService.getAlertHeadings(response);
                    
             
    }

    public AltertResponse getApiAlerts(int state_id) {

        String link = Apis.getLink(state_id);
        JSONObject response =  getResponse(link);

    

       return alertHeadingService.getAlertHeadings(response);
      

    }

}
