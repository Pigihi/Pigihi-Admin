/**
 * 
 */
package com.pigihi.service;

import java.io.IOException;
import java.net.http.HttpResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.pigihi.utility.request.rest.GETRequestSender;

/**
 * @author Ashish Sam T George
 *
 */
@Service
public class AdminCustomerFindService {
	
	@Autowired
	private GETRequestSender getRequestSender;
	
	@Value("${customerService.uri}")
	private String customerUri;
	
	@Value("${customerService.endpoint.allCustomers}")
	private String allCustomerEndpoint;
	
	@Value("${customerService.endpoint.oneCustomer.queryParam}")
	private String oneCustomerQueryParam;

	public String findAllCustomers() throws InterruptedException, IOException {

		HttpResponse<String> response = getRequestSender.send(
															customerUri.concat(allCustomerEndpoint));
		System.out.println("Response from customer service: " + response.body());
		return response.body();
		
	}

	public String findCustomer(String email) throws IOException, InterruptedException {

		HttpResponse<String> response = getRequestSender.send(customerUri, 
																oneCustomerQueryParam, email);
		System.out.println("Response from customer service: " + response.body());
		return response.body();
		
	}
	
}
