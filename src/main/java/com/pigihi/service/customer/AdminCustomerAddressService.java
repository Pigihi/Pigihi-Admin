package com.pigihi.service.customer;

import java.io.IOException;
import java.net.http.HttpResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.pigihi.library.dataConverter.service.DataConverter;
import com.pigihi.model.CustomerAddressModel;
import com.pigihi.service.PUTRequestSender;

@Service
public class AdminCustomerAddressService {
	
	@Autowired
	private PUTRequestSender putRequestSender;
	
	@Autowired
	private DataConverter dataConverter;
	
	@Value("${customerService.uri}")
	private String customerUri;
	
	@Value("${customerService.endpoint.admin.endpoint}")
	private String customerAdminEndpoint;
	
	@Value("${customerService.endpoint.admin.address.endpoint}")
	private String customerAdminAddressEndpoint;
	
	@Value("${customerService.endpoint.admin.address.queryParam}")
	private String customerAdminAddressQueryParam;

	public String changeAddress(String email, CustomerAddressModel customerAddressModel) throws IOException, InterruptedException {
		String uri = customerUri.concat(customerAdminEndpoint).concat(customerAdminAddressEndpoint);
		String jsonString = dataConverter.convertToJson(customerAddressModel);
		HttpResponse<String> response = putRequestSender.send(uri, customerAdminAddressQueryParam, 
														email, jsonString);
		System.out.println("Response obtained from customer service: " + response.body());
		return response.body();
	}

}
