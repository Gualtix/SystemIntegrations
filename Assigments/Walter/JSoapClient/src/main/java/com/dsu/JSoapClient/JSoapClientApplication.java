
package com.dsu.JSoapClient;

import org.springframework.boot.CommandLineRunner;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
//import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Bean;

//import com.dsu.JSoapClient.wsdl.GetCountryResponse;
import com.dsu.JSoapClient.wsdl.GetOrderResponse;


@SpringBootApplication
public class JSoapClientApplication {

	public static void main(String[] args) {
		SpringApplication.run(JSoapClientApplication.class, args);
	}

	@Bean
	CommandLineRunner lookup(OrderClient quoteClient) {
		return args -> {
		String orderId = "1";

		if (args.length > 0) {
			orderId = args[0];
		}

		GetOrderResponse response = quoteClient.getCustumer(orderId);
		System.err.println(response.getCustomerName());
		};
	}
	
}

