package com.pigihi.service.customer;

import java.io.IOException;
import java.net.http.HttpResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.pigihi.service.PUTRequestSender;

@Service
public class AdminCustomerNameService {
	
	@Autowired
	private PUTRequestSender putRequestSender;
	
	@Value("${customerService.uri}")
	private String customerUri;
	
	@Value("${customerService.endpoint.admin.endpoint}")
	private String customerAdminEndpoint;
	
	@Value("${customerService.endpoint.admin.fullName.endpoint}")
	private String customerNameEndpoint;
	
	@Value("${customerService.endpoint.admin.fullName.queryParam1}")
	private String customerNameQueryParam1;
	
	@Value("${customerService.endpoint.admin.fullName.queryParam2}")
	private String customerNameQueryParam2;

	public String changeFullName(String email, String fullName) throws IOException, InterruptedException {
		String uri = customerUri.concat(customerAdminEndpoint).concat(customerNameEndpoint);
		HttpResponse<String> response = putRequestSender.send(uri, 
													customerNameQueryParam1, email, 
													customerNameQueryParam2, fullName);
		System.out.println("Response obtained from customer service: " + response.body());
		return response.body();
	}
	
}
