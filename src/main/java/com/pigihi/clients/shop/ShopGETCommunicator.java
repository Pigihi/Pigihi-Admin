package com.pigihi.clients.shop;

import java.io.IOException;
import java.net.http.HttpResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import com.pigihi.clients.ClientCommunicator;
import com.pigihi.service.GETRequestSender;

public class ShopGETCommunicator implements ClientCommunicator {
	
	private String endpoint;
	
	@Autowired
	private GETRequestSender getRequestSender;
	
	@Value("${shopService.uri}")
	private String shopUri;
	
	public ShopGETCommunicator(String endpoint) {
		this.endpoint = endpoint;
	}
	
	public ShopGETCommunicator(String endpoint, String queryParam, String value) {
		this.endpoint = endpoint.concat("?").concat(queryParam).concat("=").concat(value);
	}

	@Override
	public String send() throws IOException, InterruptedException {
		HttpResponse<String> response = getRequestSender.send(shopUri.concat(this.endpoint));
		return response.body();
	}

}
