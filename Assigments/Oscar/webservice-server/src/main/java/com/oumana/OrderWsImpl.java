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


//@Features(features = "org.apache.cxf.feature.LoggingFeature")
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
		response.setCustomerName(getRandomName());
		response.getProduct().addAll(getRandomProducts());
		return response;
	}
	private static String getRandomName() {
		List<String> names = new ArrayList<>(Arrays.asList("Oscar", "Walter", "Sebastian"));
		return names.get(RANDOM.nextInt(names.size()));
	}

	private static List<Product> getRandomProducts(){
		Product product1 = new Product();
		product1.setId("1");
		product1.setPrice(102.3);
		product1.setQuantity(BigInteger.valueOf(2));
		Product product2 = new Product();
		product2.setId("2");
		product2.setPrice(1305.5);
		product2.setQuantity(BigInteger.valueOf(4));
		Product product3 = new Product();
		product3.setId("3");
		product3.setPrice(12.43);
		product3.setQuantity(BigInteger.valueOf(7));
		Product product4 = new Product();
		product4.setId("4");
		product4.setPrice(211.1);
		product4.setQuantity(BigInteger.valueOf(1));
		Product product5 = new Product();
		product5.setId("5");
		product5.setPrice(2311.6);
		product5.setQuantity(BigInteger.valueOf(5));
		Product product6 = new Product();
		product6.setId("6");
		product6.setPrice(1.15);
		product6.setQuantity(BigInteger.valueOf(4));
		List<Product> products = new ArrayList<>();
		products.add(product1);
		products.add(product2);
		products.add(product3);
		products.add(product4);
		return products.subList(0, RANDOM.nextInt(products.size()-1)+1);
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
