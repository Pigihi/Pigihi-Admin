package com.pigihi.client.shop;

import java.io.IOException;
import java.net.http.HttpResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import com.pigihi.client.ClientCommunicator;
import com.pigihi.service.DELETERequestSender;

public class ShopDELETECommunicator implements ClientCommunicator {
	
	private String endpoint;
	private String jsonBody = "";
	
	@Autowired
	private DELETERequestSender deleteRequestSender;
	
	@Value("${shopService.uri}")
	private String shopUri;
	
	public ShopDELETECommunicator(String endpoint) {
		this.endpoint = endpoint;
	}

	public ShopDELETECommunicator(String endpoint, String queryParam, String value) {
		this.endpoint = endpoint.concat("?").concat(queryParam).concat("=").concat(value);
	}

	public ShopDELETECommunicator(String endpoint, String jsonBody) {
		this.endpoint = endpoint;
		this.jsonBody = jsonBody;
	}

	public ShopDELETECommunicator(String endpoint, String queryParam, String value, String jsonBody) {
		this.endpoint = endpoint.concat("?").concat(queryParam).concat("=").concat(value);
		this.jsonBody = jsonBody;
	}

	@Override
	public String send() throws IOException, InterruptedException {
		HttpResponse<String> response = deleteRequestSender.send(shopUri.concat(this.endpoint));
		return response.body();
	}

}
