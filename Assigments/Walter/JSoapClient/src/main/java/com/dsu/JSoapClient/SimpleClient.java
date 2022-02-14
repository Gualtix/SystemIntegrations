package com.dsu.JSoapClient;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.nio.charset.Charset;

import javax.xml.soap.MessageFactory;
import javax.xml.soap.MimeHeaders;
import javax.xml.soap.SOAPConnection;
import javax.xml.soap.SOAPConnectionFactory;
import javax.xml.soap.SOAPException;
import javax.xml.soap.SOAPMessage;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;

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
            String url = "http://localhost:8080/ws";
            String soapMessage = 
            "<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:gs=\"http://spring.io/guides/gs-producing-web-service\">"+
            "   <soapenv:Header/>"+
            "   <soapenv:Body>"+
            "      <gs:getCountryRequest>"+
            "         <gs:name>Spain</gs:name>"+
            "      </gs:getCountryRequest>"+
            "   </soapenv:Body>"+
            "</soapenv:Envelope>";
            */
            SOAPMessage soapResponse = soapConnection.call(getSoapMessageFromString(soapMessage), url);
            printSOAPResponse(soapResponse);
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

    private static void printSOAPResponse(SOAPMessage soapResponse) throws Exception {
        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();
        Source sourceContent = soapResponse.getSOAPPart().getContent();
        System.out.print("\nResponse SOAP Message = ");
        StreamResult result = new StreamResult(System.out);
        transformer.transform(sourceContent, result);
    }
}
