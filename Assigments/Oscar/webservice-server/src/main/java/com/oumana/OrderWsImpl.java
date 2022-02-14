package com.oumana;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;

import org.apache.cxf.feature.Features;
import org.example.orderswsdlfile.GetOrderRequest;
import org.example.orderswsdlfile.GetOrderResponse;
import org.example.orderswsdlfile.OrdersPortType;
import org.example.orderswsdlfile.Product;
import org.example.orderswsdlfile.Status;


@Features(features = "org.apache.cxf.feature.LoggingFeature")
public class OrderWsImpl implements OrdersPortType{

	//Map<BigInteger, List<Product>> orderProducts = new HashMap<>();
	private static final List<Status> STATUS = Arrays.asList(Status.values());
	private static final Random RANDOM = new Random();
	
	public OrderWsImpl() {
		
	}

	@Override
	public GetOrderResponse getOrder(GetOrderRequest request) {
		GetOrderResponse response = new GetOrderResponse();
		String orderId = request.getOrderId();
		response.setOrderId(orderId);
		response.setOrderStatus(getRandomStatus());
		try {
			response.setOrderDate(getRandomDate());
		} catch (DatatypeConfigurationException e) {
			e.printStackTrace();
		}
		response.setCustomerName("Oscar");
		response.getProduct().addAll(getRandomProducts());
		return response;
	}
	private static List<Product> getRandomProducts(){
		Product product = new Product();
		product.setId("1");
		product.setPrice(102.3);
		product.setQuantity(BigInteger.valueOf(2));
		List<Product> products = new ArrayList<>();
		products.add(product);
		return products;
	}
	private static XMLGregorianCalendar getRandomDate() throws DatatypeConfigurationException {
		int year = RANDOM.nextInt(2) + 2020;
		int month = RANDOM.nextInt(12);
		int day = RANDOM.nextInt(30);
		
		GregorianCalendar now = new GregorianCalendar(year, month, day);
	    XMLGregorianCalendar xgcal = DatatypeFactory.newInstance().newXMLGregorianCalendar(now);
		return xgcal;
	}
	private static Status getRandomStatus() {
		return STATUS.get(RANDOM.nextInt(STATUS.size()));
	}

}
