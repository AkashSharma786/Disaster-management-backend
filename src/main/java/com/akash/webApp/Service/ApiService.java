package com.akash.webApp.Service;

import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;
import java.time.Duration;
import java.util.List;
import java.util.function.Consumer;

import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

import com.akash.webApp.Apis;
import com.akash.webApp.Model.AlertModels.AltertResponse;
import com.akash.webApp.Service.AlertServices.AlertHeadingService;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;

import jakarta.persistence.spi.TransformerException;
import reactor.core.publisher.Mono;

@Service
public class ApiService {
    String res = "";
    @Autowired
    AlertHeadingService alertHeadingService;

    private XmlMapper xmlMapper = new XmlMapper();

    public JSONObject loadResponse(WebClient client) {

        JSONObject response = client.get()
                .retrieve()

                .bodyToMono(String.class)

                .flatMap(res -> {

                    return Mono.fromCallable(() -> {
                     
                                JsonNode jsonNode = xmlMapper.readTree(res);
                            Object parsed = JSONValue.parse(jsonNode.toString());
                            if (!(parsed instanceof JSONObject)) {
                                throw new IllegalStateException("Expected JSONObject but got " + parsed);
                            }
                            return (JSONObject) parsed;
                       
                       
                    });

                }).block();

        return response;

    }

    public AltertResponse getApiAlerts() {

        WebClient client = Apis.getWeblient();
        System.out.println("Fetching alerts from NDMA API...");
        return alertHeadingService.getAlertHeadings(loadResponse(client));
                    
             
    }

    public AltertResponse getApiAlerts(int state_id) {

        WebClient client = Apis.getWebClient(state_id);
    

       return alertHeadingService.getAlertHeadings(loadResponse(client));
      

    }

}
