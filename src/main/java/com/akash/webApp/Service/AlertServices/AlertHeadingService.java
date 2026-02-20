package com.akash.webApp.Service.AlertServices;

import java.util.ArrayList;
import java.util.List;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.stereotype.Service;
import com.akash.webApp.Model.AlertModels.AlertHeading;
import com.akash.webApp.Model.AlertModels.AltertResponse;

@Service
public class AlertHeadingService {

    public AltertResponse getAlertHeadings(Object obj) {

        JSONObject jsonObject = (JSONObject) obj;
        JSONObject channel = (JSONObject) jsonObject.get("channel");
        JSONArray items = (JSONArray) channel.get("item");

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

    }

}
