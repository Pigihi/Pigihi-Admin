/**
 * 
 */
package com.pigihi.service;

import java.io.IOException;
import java.net.http.HttpResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.pigihi.model.EditCustomerModel;
import com.pigihi.utility.DataFormatter;
import com.pigihi.utility.request.rest.DELETERequestSender;
import com.pigihi.utility.request.rest.PATCHRequestSender;
import com.pigihi.utility.request.rest.PUTRequestSender;

/**
 * @author Ashish Sam T George
 *
 */
@Service
public class AdminCustomerEditService {
	
	@Autowired
	private PUTRequestSender putRequestSender;
	
	@Autowired
	private PATCHRequestSender patchRequestSender;
	
	@Autowired
	private DELETERequestSender deleteRequestSender;
	
	@Autowired
	private DataFormatter dataFormatter;
	
	@Value("${customerService.uri}")
	private String customerUri;
	
	@Value("${customerService.endpoint.enableCustomer.endpoint}")
	private String enableCustomerEndpoint;
	
	@Value("${customerService.endpoint.enableCustomer.queryParam}")
	private String enableCustomerQueryParam;
	
	@Value("${customerService.endpoint.disableCustomer.endpoint}")
	private String disableCustomerEndpoint;
	
	@Value("${customerService.endpoint.disableCustomer.queryParam}")
	private String disableCustomerQueryParam;
	
	public String editCustomer(EditCustomerModel editCustomerModel) throws IOException, InterruptedException {

		String jsonString = dataFormatter.convertToJson(editCustomerModel);
		HttpResponse<String> response = putRequestSender.send(customerUri, jsonString);
		System.out.println("Response from Customer Service: " + response.body());
		return response.body();
		
	}

	public String enableCustomer(String email) throws IOException, InterruptedException {
		
		String uri = customerUri.concat(enableCustomerEndpoint);
		HttpResponse<String> response = patchRequestSender.send(uri, 
															enableCustomerQueryParam, email);
		System.out.println("Response from customer service: " + response.body());
		return response.body();
		
	}

	public String disableCustomer(String email) throws IOException, InterruptedException {
		
		String uri = customerUri.concat(disableCustomerEndpoint);
		HttpResponse<String> response = deleteRequestSender.send(uri, 
											disableCustomerQueryParam, email);
		System.out.println("Response from customer service: " + response.body());
		
		return response.body();
	}

}
