/**
 * 
 */
package com.pigihi.service.customer;

import java.io.IOException;
import java.net.http.HttpResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.pigihi.library.dataConverter.service.DataConverter;
import com.pigihi.model.CustomerModel;
import com.pigihi.service.POSTRequestSender;

/**
 * @author Ashish Sam T George
 *
 */
@Service
public class AdminCustomerAddService {
	
	@Autowired
	private POSTRequestSender postRequestSender;
	
	@Autowired
	private DataConverter dataConverter;
	
	@Value("${authService.uri}")
	private String authUri;
	
	@Value("${authService.endpoint.addUser}")
	private String addAuthUserEndpoint;

	public String addCustomer(CustomerModel customerModel) throws IOException, InterruptedException {

		String jsonString = dataConverter.convertToJson(customerModel);
		HttpResponse<String> response = postRequestSender.send(authUri.concat(addAuthUserEndpoint), 
															jsonString);
		System.out.println("Response from Authentication Service: " + response.body());
		return response.body();

	}
	
}
