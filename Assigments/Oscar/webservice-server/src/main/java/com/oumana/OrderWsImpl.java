package com.oumana;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import org.example.orderswsdlfile.GetOrderRequest;
import org.example.orderswsdlfile.GetOrderResponse;
import org.example.orderswsdlfile.OrdersPortType;
import org.example.orderswsdlfile.Product;
import org.example.orderswsdlfile.Status;

//@Features(features = "org.apache.cxf.feature.LoggingFeature")
public class OrderWsImpl implements OrdersPortType {

	private static final List<Status> STATUS = Arrays.asList(Status.values());
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
			Product product = new Product(Integer.toString(i), Math.round(RANDOM.nextDouble(2000)*100.0)/100.0,
					BigInteger.valueOf(RANDOM.nextInt(10)));
			products.add(product);
		}
		return products.subList(0, RANDOM.nextInt(products.size() - 1) + 1);
	}

//	private static XMLGregorianCalendar getRandomDate() throws DatatypeConfigurationException {
//		int year = RANDOM.nextInt(2) + 2021;
//		int month = RANDOM.nextInt(12);
//		int day = RANDOM.nextInt(30);
//
//		GregorianCalendar now = new GregorianCalendar(year, month, day);
//		XMLGregorianCalendar xgcal = DatatypeFactory.newInstance().newXMLGregorianCalendar(now);
//		return xgcal;
//	}
//
//	private static Status getRandomStatus() {
//		return STATUS.get(RANDOM.nextInt(STATUS.size()));
//	}

}
