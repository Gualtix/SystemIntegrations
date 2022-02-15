package com.dsu.JSoapClient;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.StringWriter;
import java.nio.charset.Charset;

import javax.xml.soap.MessageFactory;
import javax.xml.soap.MimeHeaders;
import javax.xml.soap.SOAPBody;
import javax.xml.soap.SOAPConnection;
import javax.xml.soap.SOAPConnectionFactory;
import javax.xml.soap.SOAPElement;
import javax.xml.soap.SOAPException;
import javax.xml.soap.SOAPMessage;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONString; 
import org.json.JSONStringer;


import org.w3c.dom.NodeList;

// http://stackoverflow.com/questions/19291283/soap-request-to-webservice-with-java
// http://stackoverflow.com/questions/13180372/creating-a-soapmessage-from-string-xml-of-entire-soap-message
// https://gist.github.com/gliviu/48395c47fefe9cf2a8fb

public class SimpleClient {

    public static void main(String args[]) {
        try 
        {
            SOAPConnectionFactory soapConnectionFactory = SOAPConnectionFactory.newInstance();
            SOAPConnection soapConnection = soapConnectionFactory.createConnection();

            String url = "http://localhost:8080/orders/ordersservice?wsdl";
            int OrderNumber = 35;
            String soapMessage = 

            "<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:ord=\"http://www.example.org/OrdersWSDLFile/\">"+
            "   <soapenv:Header/>"+
            "   <soapenv:Body>"+
            "      <ord:getOrderRequest>"+
            "         <orderId>"+OrderNumber+"</orderId>"+
            "      </ord:getOrderRequest>"+
            "   </soapenv:Body>"+
            "</soapenv:Envelope>";

            /*
            String url_2 = "http://localhost:8080/ws";
            String soapMessage_2 = 
            "<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:gs=\"http://spring.io/guides/gs-producing-web-service\">"+
            "   <soapenv:Header/>"+
            "   <soapenv:Body>"+
            "      <gs:getCountryRequest>"+
            "         <gs:name>Spain</gs:name>"+
            "      </gs:getCountryRequest>"+
            "   </soapenv:Body>"+
            "</soapenv:Envelope>";
            */

            //printSOAPResponse(soapResponse);

            //String ns = "http://www.example.org/OrdersWSDLFile/";
            //String ns = "http://schemas.xmlsoap.org/soap/envelope/";
            String ns = "http://www.example.org/OrdersWSDLFile/";
            
            SOAPMessage soapResponse = soapConnection.call(getSoapMessageFromString(soapMessage), url);
            String XML_String = getXML_String(soapResponse);

            try {  
                JSONObject json = XML.toJSONObject(xml);   
                        String jsonString = json.toString(4);  
                        System.out.println(jsonString);  
                  
                }catch (JSONException e) {  
                // TODO: handle exception  
                System.out.println(e.toString());  
                }  
          
            

            //SOAPBody responseBody = soapResponse.getSOAPBody();
            //NodeList Body =  responseBody.getElementsByTagNameNS(ns, "fess");

            //int k = 0;
            
            soapConnection.close();
        } catch (Exception e) {
            System.err.println("Error occurred while sending SOAP Request to Server");
            e.printStackTrace();
        }
    }

    
    private static SOAPMessage getSoapMessageFromString(String xml) throws SOAPException, IOException {
        MessageFactory factory = MessageFactory.newInstance();
        SOAPMessage message = factory.createMessage(new MimeHeaders(),new ByteArrayInputStream(xml.getBytes(Charset.forName("UTF-8"))));
        return message;
    }

    private static String getXML_String(SOAPMessage soapResponse)
    {
        String tmp = "";
        try 
        {
            StringWriter writer = new StringWriter();
            StreamResult result = new StreamResult(writer);
            TransformerFactory tFactory = TransformerFactory.newInstance();
            Transformer transformer = tFactory.newTransformer();
            Source sourceContent = soapResponse.getSOAPPart().getContent();
            transformer.transform(sourceContent,result);
            String strResult = writer.toString();
            return strResult;
        } catch (Exception e) 
        {
            e.printStackTrace();
        }
        return tmp;
    }
    

    /*
    private static void printSOAPResponse(SOAPMessage soapResponse) throws Exception {
        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();
        Source sourceContent = soapResponse.getSOAPPart().getContent();
        System.out.print("\nResponse SOAP Message = ");
        StreamResult result = new StreamResult(System.out);
        transformer.transform(sourceContent, result);
    }
    */
}

