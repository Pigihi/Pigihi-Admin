package com.pigihi.client.customer;


import java.io.IOException;
import java.net.http.HttpResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import com.pigihi.client.ClientCommunicator;
import com.pigihi.service.PUTRequestSender;


public class CustomerPUTCommunicator implements ClientCommunicator {

	private String endpoint;
	private String jsonBody = "";
	
	@Autowired
	private PUTRequestSender putRequestSender;

	@Value("${customerService.uri}")
	private String customerUri;

	public CustomerPUTCommunicator(String endpoint) {
		this.endpoint = endpoint;
	}

	public CustomerPUTCommunicator(String endpoint, String queryParam, String value) {
		this.endpoint = endpoint.concat("?").concat(queryParam).concat("=").concat(value);
	}

	public CustomerPUTCommunicator(String endpoint, String queryParam1, String value1, String queryParam2,
			String value2) {
		this.endpoint = endpoint.concat("?")
				.concat(queryParam1)
				.concat("=")
				.concat(value1)
				.concat("&")
				.concat(queryParam2)
				.concat("=")
				.concat(value2);
	}

	public CustomerPUTCommunicator(String endpoint, String jsonBody) {
		this.endpoint = endpoint;
		this.jsonBody = jsonBody;
	}

	public CustomerPUTCommunicator(String endpoint, String queryParam, String value, String jsonBody) {
		this.endpoint = endpoint.concat("?").concat(queryParam).concat("=").concat(value);
		this.jsonBody = jsonBody;
	}

	@Override
	public String send() throws IOException, InterruptedException {
		HttpResponse<String> response = putRequestSender.send(customerUri.concat(endpoint), this.jsonBody);
		return response.body();
	}

}
