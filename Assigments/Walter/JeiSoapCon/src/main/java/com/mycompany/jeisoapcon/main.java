package com.mycompany.jeisoapcon;
public class main 
{

    public static void main(String[] args) throws Exception 
    {
        SoapClient cli = new SoapClient();
        Order ord = new Order();
        String xml = ord.getXmlString_NewOrder("1","16/04/2016","Walter Morales", "Pending");

        Order tmp = cli.DoPost(xml);
        tmp.PrintOrder();
    }
    
}
