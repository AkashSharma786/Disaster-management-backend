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

import org.json.simple.JSONValue;
import org.springframework.beans.factory.annotation.Autowired;
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
    @Autowired
    Apis apis;

    public Object loadResponse(WebClient client) throws Exception {
        XmlMapper xmlMapper = new XmlMapper();

        Mono<String> response = client.get()
                .retrieve()
                .bodyToMono(String.class);

        Consumer<String> dataConsumer = new Consumer<String>() {
            public void accept(String data) {
                // System.out.println(data);
                res += res.concat(data);
                // System.out.print(res);
            }
        };

        response.subscribe(dataConsumer);
        response.block();

        JsonNode jsonNode = xmlMapper.readTree(res);
        Object obj = JSONValue.parse(jsonNode.toString());

        return obj;

    }

    public AltertResponse getApiAlerts() throws Exception {

        WebClient client = apis.getWeblient();

        return alertHeadingService.getAlertHeadings(loadResponse(client));

    }

    public AltertResponse getApiAlerts(int state_id) throws Exception {

        WebClient client = apis.getWebClient(state_id);
        
        return alertHeadingService.getAlertHeadings(loadResponse(client));

    }

}
