package com.oumana;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.example.orderswsdlfile.GetOrderRequest;
import org.example.orderswsdlfile.GetOrderResponse;
import org.example.orderswsdlfile.OrdersPortType;
import org.example.orderswsdlfile.Product;

public class OrderWsImpl implements OrdersPortType{

	Map<BigInteger, List<Product>> orderProducts = new HashMap<>();
	
	
	public OrderWsImpl() {
		
	}

	@Override
	public GetOrderResponse getOrder(GetOrderRequest request) {
		String orderId = request.getOrderId();
		return null;
	}

}
