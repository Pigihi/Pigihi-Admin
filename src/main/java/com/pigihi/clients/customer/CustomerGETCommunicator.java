package com.pigihi.clients.customer;


import java.io.IOException;
import java.net.http.HttpResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import com.pigihi.clients.ClientCommunicator;
import com.pigihi.service.GETRequestSender;


public class CustomerGETCommunicator implements ClientCommunicator {

	private String endpoint;

	public CustomerGETCommunicator(String endpoint) {
		this.endpoint = endpoint;
	}

	public CustomerGETCommunicator(String endpoint, String queryParam, String value) {
		this.endpoint = endpoint.concat("?").concat(queryParam).concat("=").concat(value);
	}

	@Autowired
	private GETRequestSender getRequestSender;

	@Value("${customerService.uri}")
	private String customerUri;

	@Override
	public String send() throws IOException, InterruptedException {
		HttpResponse<String> response = getRequestSender.send(customerUri.concat(this.endpoint));
		return response.body();

	}

}
