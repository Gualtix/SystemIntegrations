package com.mycompany.jeisoapcon;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.StringWriter;
import java.nio.charset.Charset;
import java.util.LinkedList;


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
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import org.json.XML;

class Product {
    String id;
    String quantity;
    String price;
}

class Order {
    public String orderId;
    public String orderDate;
    public String customerName;
    public String orderStatus;
    public LinkedList<Product> products = new LinkedList<Product>();

    public Order() {

    }

    public void PrintOrder() {
        System.out.println("Order ID: " + orderId);
        System.out.println("Order Date: " + orderDate);
        System.out.println("Customer Name: " + customerName);
        System.out.println("Order Status: " + orderStatus);
        System.out.println("Products:");
        for (Product p : products) {
            System.out.println("\t id: " + p.id + " quantity: " + p.quantity + " price: " + p.price);
        }

    }

    public String getXmlString_NewOrder(String orderId, String orderDate, String custumerName, String orderStatus) {
        /*
         * String xml =
         * "<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:ord=\"http://www.example.org/OrdersWSDLFile/\">"
         * + "   <soapenv:Header/>"
         * + "   <soapenv:Body>"
         * + "      <ord:getOrderRequest>"
         * + "         <orderId>" + orderId + "</orderId>"
         * + "         <orderDate>" + orderDate + "</orderDate>"
         * + "         <custumerName>" + custumerName + "</custumerName>"
         * + "         <orderStatus>" + orderStatus + "</orderStatus>"
         * + "      </ord:getOrderRequest>"
         * + "   </soapenv:Body>"
         * + "</soapenv:Envelope>";
         * return xml;
         */

        String xml = 
                "<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:ord=\"http://www.example.org/OrdersWSDLFile/\">"
                + "   <soapenv:Header/>"
                + "   <soapenv:Body>"
                + "      <ord:getOrderRequest>"
                + "         <orderId>" + orderId + "</orderId>"
                + "      </ord:getOrderRequest>"
                + "   </soapenv:Body>"
                + "</soapenv:Envelope>";
        return xml;
    }
}

public class SoapClient {
    String URI = "http://localhost:8080/orders/ordersservice?wsdl";

    public Order FillOrder(JSONObject jsonObject) {

        Order ord = new Order();
        ord.orderId = String.valueOf(jsonObject.get("orderId"));
        ord.orderDate = (String)jsonObject.get("orderDate");
        ord.customerName = (String) jsonObject.get("customerName");
        ord.orderStatus = (String) jsonObject.get("orderStatus");

        Object test = null;
        try
        {
            test = jsonObject.get("product");
        }
        catch(JSONException e){
            System.out.println("No products");
            return ord;
        }

        if(test instanceof JSONObject){
            Product prod = new Product();
            prod.id = String.valueOf(jsonObject.getJSONObject("product").get("id"));
            prod.quantity = String.valueOf(jsonObject.getJSONObject("product").get("quantity"));
            prod.price = String.valueOf(jsonObject.getJSONObject("product").get("price"));
            ord.products.add(prod);
            return ord;
        }

        if(test instanceof JSONArray)
        {
            JSONArray products = jsonObject.getJSONArray("product");
            for (int i = 0; i < products.length(); i++) {
                Product prod = new Product();
                prod.id = String.valueOf(products.getJSONObject(i).get("id"));
                prod.quantity = String.valueOf(products.getJSONObject(i).get("quantity"));
                prod.price = String.valueOf(products.getJSONObject(i).get("price"));
                ord.products.add(prod);
            }
            return ord;
        }
        return ord;
    }

    public Order DoPost(String XmlQuery) throws UnsupportedOperationException, SOAPException, IOException {
        SOAPConnectionFactory soapConnectionFactory = SOAPConnectionFactory.newInstance();
        SOAPConnection soapConnection = soapConnectionFactory.createConnection();
        SOAPMessage soapResponse = soapConnection.call(getSoapMessageFromString(XmlQuery), this.URI);
        String XML_String_Response = getXML_String(soapResponse);

        String tmpse = "<soap:Envelope xmlns:soap=\"http://schemas.xmlsoap.org/soap/envelope/\">"
        +"   <soap:Body>"
        +"      <ns2:getOrderResponse xmlns:ns2=\"http://www.example.org/OrdersWSDLFile/\">"
        +"         <orderId>524</orderId>"
        +"         <orderStatus>STARTED</orderStatus>"
        +"         <orderDate>2020-11-07-06:00</orderDate>"
        +"         <customerName>Oscar</customerName>"
        +"            <product>"
        +"               <id>1</id>"
        +"               <quantity>1</quantity>"
        +"               <price>100</price>"
        +"            </product>"
        +"            <product>"
        +"               <id>2</id>"
        +"               <quantity>2</quantity>"
        +"               <price>200</price>"
        +"            </product>"
        +"            <product>"
        +"               <id>3</id>"
        +"               <quantity>3</quantity>"
        +"               <price>300</price>"
        +"            </product>"
        +"      </ns2:getOrderResponse>"
        +"   </soap:Body>"
        +"</soap:Envelope>";

        try {
            JSONObject jsonObject = XML.toJSONObject(XML_String_Response);
            JSONObject tmp = jsonObject.getJSONObject("soap:Envelope").getJSONObject("soap:Body").getJSONObject("ns2:getOrderResponse");
            Order ord = FillOrder(tmp);
            return ord;
            // String jsonString = json.toString(4);
            // System.out.println(jsonString);

        } catch (JSONException e) {
            System.out.println(e.toString());
        }
        return null;
    }

    public SoapClient() {

        /*
         * try
         * {
         * SOAPConnectionFactory soapConnectionFactory =
         * SOAPConnectionFactory.newInstance();
         * SOAPConnection soapConnection = soapConnectionFactory.createConnection();
         * 
         * String url = "http://localhost:8080/orders/ordersservice?wsdl";
         * int OrderNumber = 35;
         * String soapMessage =
         * "<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:ord=\"http://www.example.org/OrdersWSDLFile/\">"
         * + "   <soapenv:Header/>"
         * + "   <soapenv:Body>"
         * + "      <ord:getOrderRequest>"
         * + "         <orderId>" + OrderNumber + "</orderId>"
         * + "      </ord:getOrderRequest>"
         * + "   </soapenv:Body>"
         * + "</soapenv:Envelope>";
         * 
         * SOAPMessage soapResponse =
         * soapConnection.call(getSoapMessageFromString(soapMessage), url);
         * String XML_String = getXML_String(soapResponse);
         * 
         * 
         * soapConnection.close();
         * } catch (Exception e)
         * {
         * System.err.println("Error occurred while sending SOAP Request to Server");
         * e.printStackTrace();
         * }
         */

    }

    private static SOAPMessage getSoapMessageFromString(String xml) throws SOAPException, IOException {
        MessageFactory factory = MessageFactory.newInstance();
        SOAPMessage message = factory.createMessage(new MimeHeaders(),
                new ByteArrayInputStream(xml.getBytes(Charset.forName("UTF-8"))));
        return message;
    }

    private static String getXML_String(SOAPMessage soapResponse) {
        String tmp = "";
        try {
            StringWriter writer = new StringWriter();
            StreamResult result = new StreamResult(writer);
            TransformerFactory tFactory = TransformerFactory.newInstance();
            Transformer transformer = tFactory.newTransformer();
            Source sourceContent = soapResponse.getSOAPPart().getContent();
            transformer.transform(sourceContent, result);
            String strResult = writer.toString();
            return strResult;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return tmp;
    }

}
