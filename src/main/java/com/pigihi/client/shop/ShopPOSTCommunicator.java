package com.pigihi.client.shop;

import java.io.IOException;
import java.net.http.HttpResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import com.pigihi.client.ClientCommunicator;
import com.pigihi.service.POSTRequestSender;

public class ShopPOSTCommunicator implements ClientCommunicator {
	
	private String endpoint;
	private String jsonBody = "";
	
	@Autowired
	private POSTRequestSender postRequestSender;
	
	@Value("${shopService.uri}")
	private String shopUri;
	
	public ShopPOSTCommunicator(String endpoint) {
		this.endpoint = endpoint;
	}
	
	public ShopPOSTCommunicator(String endpoint, String queryParam, String value) {
		this.endpoint = endpoint.concat("?").concat(queryParam).concat("=").concat(value);
	}

	public ShopPOSTCommunicator(String endpoint, String jsonBody) {
		this.endpoint = endpoint;
		this.jsonBody = jsonBody;
	}

	public ShopPOSTCommunicator(String endpoint, String queryParam, String value, String jsonBody) {
		this.endpoint = endpoint.concat("?").concat(queryParam).concat("=").concat(value);
		this.jsonBody = jsonBody;
	}

	@Override
	public String send() throws IOException, InterruptedException {
		HttpResponse<String> response = postRequestSender.send(shopUri.concat(this.endpoint), this.jsonBody);
		return response.body();
	}

}
