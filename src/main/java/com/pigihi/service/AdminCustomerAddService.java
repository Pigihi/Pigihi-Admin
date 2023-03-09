/**
 * 
 */
package com.pigihi.service;

import java.io.IOException;
import java.net.http.HttpResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.pigihi.model.CustomerModel;
import com.pigihi.utility.DataFormatter;
import com.pigihi.utility.request.rest.POSTRequestSender;

/**
 * @author Ashish Sam T George
 *
 */
@Service
public class AdminCustomerAddService {
	
	@Autowired
	private POSTRequestSender postRequestSender;
	
	@Autowired
	private DataFormatter dataFormatter;
	
	@Value("${authService.uri}")
	private String authUri;
	
	@Value("${authService.endpoint.addCustomer}")
	private String addAuthCustomerEndpoint;

	public String addCustomer(CustomerModel customerModel) throws IOException, InterruptedException {

		String jsonString = dataFormatter.convertToJson(customerModel);
		HttpResponse<String> response = postRequestSender.send(authUri.concat(addAuthCustomerEndpoint), 
															jsonString);
		System.out.println("Response from Authentication Service: " + response.body());
		return response.body();

	}
	
}
