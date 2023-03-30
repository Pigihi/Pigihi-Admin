package com.pigihi.clients.shop;

import java.io.IOException;
import java.net.http.HttpResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import com.pigihi.clients.ClientCommunicator;
import com.pigihi.service.PATCHRequestSender;

public class ShopPATCHCommunicator implements ClientCommunicator {
	
	private String endpoint;
	private String jsonBody = "";
	
	@Autowired
	private PATCHRequestSender patchRequestSender;
	
	@Value("${customerService.uri}")
	private String customerUri;
	
	public ShopPATCHCommunicator(String endpoint) {
		this.endpoint = endpoint;
	}

	public ShopPATCHCommunicator(String endpoint, String queryParam, String value) {
		this.endpoint = endpoint.concat("?").concat(queryParam).concat("=").concat(value);
	}

	public ShopPATCHCommunicator(String endpoint, String jsonBody) {
		this.endpoint = endpoint;
		this.jsonBody = jsonBody;
	}

	public ShopPATCHCommunicator(String endpoint, String queryParam, String value, String jsonBody) {
		this.endpoint = endpoint.concat("?").concat(queryParam).concat("=").concat(value);
		this.jsonBody = jsonBody;
	}

	@Override
	public String send() throws IOException, InterruptedException {
		HttpResponse<String> response = patchRequestSender.send(customerUri.concat(this.endpoint), this.jsonBody);
		return response.body();
	}

}
