package com.akash.webApp.Service.AlertServices;

import java.io.StringReader;
import java.io.StringWriter;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.List;

import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathFactory;

import org.springframework.stereotype.Service;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

import com.akash.webApp.Model.AlertModels.AlertHeading;
import java.text.Normalizer;

@Service
public class AlertHeadingService {

    public void getAlertHeadings(String xml) {
        
        String norm = Normalizer.normalize(xml, Normalizer.Form.NFD);
        String noDiacritics = norm.replaceAll("\\p{M}", "?");            // removes accents
         xml = noDiacritics.replaceAll("[^\\x00-\\x7F]", "?"); 

        try {

            System.out.println(xml);

            XPath xPath = XPathFactory.newInstance().newXPath();
            XPathExpression eXPathExpression = xPath.compile("/rss/channel/item");
            InputSource inputSource = new InputSource(new StringReader(xml));
            NodeList nodeList = (NodeList) eXPathExpression.evaluate(inputSource, XPathConstants.NODESET);

            for(int i = 0; i < nodeList.getLength(); i++)
            {

                
                Node node = nodeList.item(i);
                
                System.out.println(node);

                if(node.getNodeType() == Node.ELEMENT_NODE)
                {   NodeList elements = node.getChildNodes();

                    for(int j = 0; j < elements.getLength(); j++)
                    {
                        Node childNode = elements.item(j);
                        if(childNode.getNodeType() == Node.ELEMENT_NODE)
                        {
                            Element element = (Element) childNode;
                            System.out.print(element.getTagName() +" -> "+ element.getTextContent());
                        }
                    }
                    
                }
                else
                    System.out.print(false);
            }
            System.out.println("filteredXml");

        } catch (Exception e) {
            System.out.println(e);
        }
    }

}
