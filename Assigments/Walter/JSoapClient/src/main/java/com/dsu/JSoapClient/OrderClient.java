 
package com.dsu.JSoapClient;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.ws.client.core.support.WebServiceGatewaySupport;
import org.springframework.ws.soap.client.core.SoapActionCallback;

import com.dsu.JSoapClient.wsdl.GetOrderResponse;
import com.dsu.JSoapClient.wsdl.GetOrderRequest;

public class OrderClient extends WebServiceGatewaySupport {

  private static final Logger log = LoggerFactory.getLogger(OrderClient.class);

 
  public GetOrderResponse getCustumer(String orderId) 
  {
    GetOrderRequest request = new GetOrderRequest();
    request.setOrderId(orderId);

    log.info("Requesting location for " + orderId);

    GetOrderResponse response = (GetOrderResponse) getWebServiceTemplate()
        .marshalSendAndReceive("http://localhost:8080/orders/ordersservice?wsdl", request,
            new SoapActionCallback(
                "http://localhost:8080/orders/ordersservice?wsdl"));

    return response;
  }
  

}

