package com.akash.webApp.Service.AlertServices;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.akash.webApp.Model.District;
import com.akash.webApp.Model.AlertModels.AlertHeading;
import com.akash.webApp.Model.AlertModels.AlertItem;
import com.akash.webApp.Model.AlertModels.AltertResponse;
import com.akash.webApp.Repository.AlertItemRepo;
import com.akash.webApp.Repository.DistrictRepo;
import com.akash.webApp.Repository.StateorUtRepo;
import com.akash.webApp.Service.ApiService;

import lombok.experimental.PackagePrivate;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class AlertItemService {

    
    @Autowired
    private ApiService apiService;

    @Autowired
    private DistrictRepo districtRepo;
    @Autowired
    private StateorUtRepo stateorUtRepo;
    @Autowired
    private AlertItemRepo alertItemRepo;

 
    public AlertItem parsAlertItem(JSONObject data, Integer stateId) {
        
                // System.out.println(jsonObject.toString());
                JSONObject alert = null;
                if (data.get("info") instanceof JSONObject) {
                    alert = (JSONObject) data.get("info");
                    System.out.println("object");

                } else {
                    JSONArray alerts = (JSONArray) data.get("info");
                    for (int i = 0; i < alerts.size(); i++) {
                        JSONObject alertItem = (JSONObject) alerts.get(i);
                        String language = (String) alertItem.get("language");
                        if (language.equals("en-IN")) {
                            alert = alertItem;
                        }
                    }
                }

                // System.out.println(alert.toJSONString());

                String event = (String) alert.get("event");
                String urgency = (String) alert.get("urgency");
                String severity = (String) alert.get("severity");
                String certainty = (String) alert.get("certainty");


                OffsetDateTime effective = OffsetDateTime.parse( (String) alert.get("effective"));

                OffsetDateTime expires = OffsetDateTime.parse((String) alert.get("expires"));


                String headline = (String) alert.get("headline");
                String instruction = (String) alert.get("instruction");

                System.out.println(event + " ," + urgency + " ," + severity + " ," + certainty + " ," + effective + " ,"
                        + expires + " ," + headline + " ," + instruction);

                List<Object> areas;

                if (alert.get("area") instanceof JSONObject) {
                    List<Object> areaArr = new ArrayList<>();
                    areaArr.add(alert.get("area"));
                    areas = areaArr;
                } else {
                    areas = (JSONArray) alert.get("area");
                }
                List<District> districts = new ArrayList<>();
                for (Object areaObj : areas) {

                    List<Object> geoCodes;
                    JSONObject area = (JSONObject) areaObj;

                    if (area.get("geocode") != null) {

                        if (area.get("geocode") instanceof JSONObject) {
                            List<Object> objList = new ArrayList<>();
                            objList.add(area.get("geocode"));
                            geoCodes = objList;
                        } else {
                            JSONObject areaJsonObj = (JSONObject) area;

                            geoCodes = (JSONArray) areaJsonObj.get("geocode");
                        }

                        for (Object geoCode : geoCodes) {
                            JSONObject geocode = (JSONObject) geoCode;
                            System.out.println(geocode.toString());

                            String valueName = (String) geocode.get("valueName");
                            if (valueName.equals("LGD District Code")) {
                                Integer lgdCode = Integer.parseInt((String) geocode.get("value"));

                                if (!districtRepo.findById(lgdCode).isEmpty())
                                    districts.add(districtRepo.findById(lgdCode).get());
                            }

                        }
                    } else {

                        if (!stateorUtRepo.findById(stateId).isEmpty()) {
                            List<District> allStateDistricts = districtRepo
                                    .findByStateorUt(stateorUtRepo.findById(stateId).get());
                            districts.addAll(allStateDistricts);
                        }

                    }

                    for (int i = 0; i < districts.size(); i++) {
                        System.out.println("LGD Codes -> " + districts.get(i).toString());
                    }

                }

                return new AlertItem(event, urgency, severity, certainty, headline, instruction, effective, expires, districts);


    }



    public Mono<AlertItem> getAlertItem(Integer stateId, Integer index)  {

        Mono<AltertResponse> response = apiService.getApiAlerts(stateId);

        return response
       
        .flatMap(res -> {

            // System.out.println(res);

            List<AlertHeading> alertHeadings = res.getAlertHeadings();
            
            
          
            if ( alertHeadings == null ||alertHeadings.size() == 0) {
                throw new IndexOutOfBoundsException();
            }
            // System.out.println(alertHeadings);

            if (index > alertHeadings.size())
                throw new IndexOutOfBoundsException();

            String link = alertHeadings.get(index).getLink();
            System.out.println(link);

            WebClient client = WebClient.create(link);

            Mono<JSONObject>  obj  = apiService.loadResponse(client);
          
            // System.out.println(obj);

            Mono<AlertItem> result = obj.map(data -> {

                // System.out.println(data.toString());

                return parsAlertItem(data, stateId);
               
               

                
            
            
            }
        
        );

            return result;

        });

    }


public Flux<AlertItem> getAlerts(Integer stateId) {

    return apiService.getApiAlerts(stateId)   // Mono<AltertResponse>
            .flatMapMany(res -> {

                List<AlertHeading> headings = res.getAlertHeadings();

                if (headings == null || headings.isEmpty()) {
                    return Flux.empty();
                }

                return Flux.fromIterable(headings)
                     
                        .flatMap(heading -> {

                            String link = heading.getLink();
                            WebClient client = WebClient.create(link);

                            return apiService.loadResponse(client)   // Mono<Object>
                                    .map(data -> parsAlertItem(data, stateId));

                        });

            });
}



public Mono<String> saveAlertItem(Integer stateId, Integer index)
{
    Mono<AlertItem> alertItem = getAlertItem(stateId, index);

    return alertItem.map(item ->{
        alertItemRepo.save(item);
        return "success";

    }).onErrorResume(e -> Mono.error(e));

}

}