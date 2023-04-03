package com.pigihi.client.customer;

import java.io.IOException;
import java.net.http.HttpResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import com.pigihi.client.ClientCommunicator;
import com.pigihi.service.DELETERequestSender;
import com.pigihi.service.PATCHRequestSender;

public class CustomerDELETECommunicator implements ClientCommunicator {
	
	private String endpoint;
	private String jsonBody = "";
	
	@Autowired
	private DELETERequestSender deleteRequestSender;
	
	@Value("${customerService.uri}")
	private String customerUri;

	public CustomerDELETECommunicator(String endpoint) {
		this.endpoint = endpoint;
	}

	public CustomerDELETECommunicator(String endpoint, String queryParam, String value) {
		this.endpoint = endpoint.concat("?").concat(queryParam).concat("=").concat(value);
	}

	public CustomerDELETECommunicator(String endpoint, String jsonBody) {
		this.endpoint = endpoint;
		this.jsonBody = jsonBody;
	}

	public CustomerDELETECommunicator(String endpoint, String queryParam, String value, String jsonBody) {
		this.endpoint = endpoint.concat("?").concat(queryParam).concat("=").concat(value);
		this.jsonBody = jsonBody;
	}

	@Override
	public String send() throws IOException, InterruptedException {
		HttpResponse<String> response = deleteRequestSender.send(customerUri.concat(this.endpoint));
		return response.body();
	}

}
