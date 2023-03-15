package com.pigihi.service.customer;

import java.io.IOException;
import java.net.http.HttpResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.pigihi.service.PUTRequestSender;

@Service
public class AdminCustomerProfileImageService {
	
	@Autowired
	private PUTRequestSender putRequestSender;
	
	@Value("${customerService.uri}")
	private String customerUri;
	
	@Value("${customerService.endpoint.admin.endpoint}")
	private String customerAdminEndpoint;
	
	@Value("${customerService.endpoint.admin.profileImage.endpoint}")
	private String customerProfileImageEndpoint;
	
	@Value("${customerService.endpoint.admin.profileImage.queryParam1}")
	private String customerProfileImageQueryParam1;
	
	@Value("${customerService.endpoint.admin.profileImage.queryParam2}")
	private String customerProfileImageQueryParam2;

	public String changeProfileImage(String email, String imageUrl) throws IOException, InterruptedException {
		String uri = customerUri.concat(customerAdminEndpoint).concat(customerProfileImageEndpoint);
		HttpResponse<String> response = putRequestSender.send(uri, 
													customerProfileImageQueryParam1, email, 
													customerProfileImageQueryParam2, imageUrl);
		System.out.println("Response obtained from customer service: " + response.body());
		return response.body();
	}

}
