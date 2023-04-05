package com.pigihi.client.customer;


import java.io.IOException;
import java.net.http.HttpResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import com.pigihi.client.ClientCommunicator;
import com.pigihi.service.POSTRequestSender;


public class CustomerPOSTCommunicator implements ClientCommunicator {

	private String endpoint;
	private String jsonBody = "";
	
	@Autowired
	private POSTRequestSender postRequestSender;

	@Value("${customerService.uri}")
	private String customerUri;

	public CustomerPOSTCommunicator(String endpoint) {
		this.endpoint = endpoint;
	}

	public CustomerPOSTCommunicator(String endpoint, String queryParam, String value) {
		this.endpoint = endpoint.concat("?").concat(queryParam).concat("=").concat(value);
	}

	public CustomerPOSTCommunicator(String endpoint, String jsonBody) {
		this.endpoint = endpoint;
		this.jsonBody = jsonBody;
	}

	public CustomerPOSTCommunicator(String endpoint, String queryParam, String value, String jsonBody) {
		this.endpoint = endpoint.concat("?").concat(queryParam).concat("=").concat(value);
		this.jsonBody = jsonBody;
	}

	@Override
	public String send() throws IOException, InterruptedException {
		HttpResponse<String> response = postRequestSender.send(customerUri.concat(this.endpoint), this.jsonBody);
		return response.body();
	}

}
