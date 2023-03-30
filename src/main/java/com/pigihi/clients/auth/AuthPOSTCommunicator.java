package com.pigihi.clients.auth;

import java.io.IOException;
import java.net.http.HttpResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import com.pigihi.clients.ClientCommunicator;
import com.pigihi.service.POSTRequestSender;

public class AuthPOSTCommunicator implements ClientCommunicator {
	
	private String endpoint;
	private String jsonBody = "";

	public AuthPOSTCommunicator(String endpoint) {
		this.endpoint = endpoint;
	}

	public AuthPOSTCommunicator(String endpoint, String queryParam, String value) {
		this.endpoint = endpoint.concat("?").concat(queryParam).concat("=").concat(value);
	}

	public AuthPOSTCommunicator(String endpoint, String jsonBody) {
		this.endpoint = endpoint;
		this.jsonBody = jsonBody;
	}

	public AuthPOSTCommunicator(String endpoint, String queryParam, String value, String jsonBody) {
		this.endpoint = endpoint.concat("?").concat(queryParam).concat("=").concat(value);
		this.jsonBody = jsonBody;
	}

	@Autowired
	private POSTRequestSender postRequestSender;

	@Value("${authService.uri}")
	private String authUri;

	@Override
	public String send() throws IOException, InterruptedException {
		HttpResponse<String> response = postRequestSender.send(authUri.concat(this.endpoint), this.jsonBody);
		return response.body();
	}

}
