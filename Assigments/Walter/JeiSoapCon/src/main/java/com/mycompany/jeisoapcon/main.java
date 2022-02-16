package com.mycompany.jeisoapcon;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class main 
{
    public static void main(String[] args) throws Exception 
    {
        SoapClient cli = new SoapClient();
        Order ord = new Order();

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        System.out.println("--- SOAP Consumer App ---");
        System.out.println("");

        System.out.println("Enter Order ID:");
        String orderId = reader.readLine();
        System.out.println("");

        System.out.println("Enter Order Date:");
        String orderDate = reader.readLine();
        System.out.println("");

        System.out.println("Enter Order Customer Name:");
        String customerName = reader.readLine();
        System.out.println("");

        System.out.println("Enter Order Status:");
        String orderStatus = reader.readLine();
        System.out.println("");


        String xml = ord.getXmlString_NewOrder(orderId, orderDate, customerName, orderStatus);
        Order tmp = cli.DoPost(xml);

        System.out.println("--- SOAP Comnication Success ---");
        System.out.println("");
        tmp.PrintOrder();
    }
}
