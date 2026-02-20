package com.akash.webApp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;


@Component
public class Apis {

    private List<String> links = List.of(
        "https://sachet.ndma.gov.in/cap_public_website/rss/rss_india.xml",
        "https://sachet.ndma.gov.in/cap_public_website/rss/rss_andaman.xml",
        "https://sachet.ndma.gov.in/cap_public_website/rss/rss_andhra.xml",
        "https://sachet.ndma.gov.in/cap_public_website/rss/rss_arunachal.xml",
        "https://sachet.ndma.gov.in/cap_public_website/rss/rss_assam.xml",
        "https://sachet.ndma.gov.in/cap_public_website/rss/rss_bihar.xml",
        "https://sachet.ndma.gov.in/cap_public_website/rss/rss_chandigarh.xml",
        "https://sachet.ndma.gov.in/cap_public_website/rss/rss_chhattisgarh.xml",
        "https://sachet.ndma.gov.in/cap_public_website/rss/rss_dadra.xml",
        "https://sachet.ndma.gov.in/cap_public_website/rss/rss_delhi.xml",
        "https://sachet.ndma.gov.in/cap_public_website/rss/rss_goa.xml",
        "https://sachet.ndma.gov.in/cap_public_website/rss/rss_gujarat.xml",
        "https://sachet.ndma.gov.in/cap_public_website/rss/rss_haryana.xml",
        "https://sachet.ndma.gov.in/cap_public_website/rss/rss_himachal.xml",
        "https://sachet.ndma.gov.in/cap_public_website/rss/rss_jammu.xml",
        "https://sachet.ndma.gov.in/cap_public_website/rss/rss_jharkhand.xml",
        "https://sachet.ndma.gov.in/cap_public_website/rss/rss_karnataka.xml",
        "https://sachet.ndma.gov.in/cap_public_website/rss/rss_kerala.xml",
        "https://sachet.ndma.gov.in/cap_public_website/rss/rss_ladakh.xml",
        "https://sachet.ndma.gov.in/cap_public_website/rss/rss_lakshadweep.xml",
        "https://sachet.ndma.gov.in/cap_public_website/rss/rss_madhya.xml",
        "https://sachet.ndma.gov.in/cap_public_website/rss/rss_maharashtra.xml",
        "https://sachet.ndma.gov.in/cap_public_website/rss/rss_manipur.xml",
        "https://sachet.ndma.gov.in/cap_public_website/rss/rss_meghalaya.xml",
        "https://sachet.ndma.gov.in/cap_public_website/rss/rss_mizoram.xml",
        "https://sachet.ndma.gov.in/cap_public_website/rss/rss_nagaland.xml",
        "https://sachet.ndma.gov.in/cap_public_website/rss/rss_odisha.xml",
        "https://sachet.ndma.gov.in/cap_public_website/rss/rss_puducherry.xml",
        "https://sachet.ndma.gov.in/cap_public_website/rss/rss_punjab.xml",
        "https://sachet.ndma.gov.in/cap_public_website/rss/rss_rajasthan.xml",
        "https://sachet.ndma.gov.in/cap_public_website/rss/rss_sikkim.xml",
        "https://sachet.ndma.gov.in/cap_public_website/rss/rss_tamil.xml",
        "https://sachet.ndma.gov.in/cap_public_website/rss/rss_telangana.xml",
        "https://sachet.ndma.gov.in/cap_public_website/rss/rss_tripura.xml",
        "https://sachet.ndma.gov.in/cap_public_website/rss/rss_uttarakhand.xml",
        "https://sachet.ndma.gov.in/cap_public_website/rss/rss_uttar.xml",
        "https://sachet.ndma.gov.in/cap_public_website/rss/rss_west.xml"
    );

    public WebClient  getWeblient(){
       
        return  WebClient.create("https://sachet.ndma.gov.in/cap_public_website/rss/rss_india.xml");
    }
    public WebClient getWebClient(int state_index){
       return WebClient.create(links.get(state_index)); 
    }
    
}
