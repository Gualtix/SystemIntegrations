package com.oumana;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.example.orderswsdlfile.GetOrderRequest;
import org.example.orderswsdlfile.GetOrderResponse;
import org.example.orderswsdlfile.OrdersPortType;
import org.example.orderswsdlfile.Product;

//@Features(features = "org.apache.cxf.feature.LoggingFeature")
public class OrderWsImpl implements OrdersPortType {

	private static final Random RANDOM = new Random();

	public OrderWsImpl() {

	}

	@Override
	public GetOrderResponse getOrder(GetOrderRequest request) {
		GetOrderResponse response = new GetOrderResponse();
		response.setOrderId(request.getOrderId());
		response.setOrderStatus(request.getOrderStatus());
		response.setOrderDate(request.getOrderDate());
		response.setCustomerName(request.getCustomerName());
		response.getProduct().addAll(getRandomProducts());
		return response;
	}

	private static List<Product> getRandomProducts() {
		List<Product> products = new ArrayList<>();
		for (int i = 1; i < 100; i++) {
			org.example.orderswsdlfile.Product product = new Product();
			product.setId(Integer.toString(i));
			product.setPrice(Math.round(1000.0*RANDOM.nextDouble()*100.0)/100.0);
			product.setQuantity(BigInteger.valueOf(RANDOM.nextInt(10)));
			products.add(product);
		}
		return products.subList(0, RANDOM.nextInt(products.size() - 1) + 1);
	}


}
