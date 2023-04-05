/**
 * 
 */
package com.pigihi.service.shop;

import java.io.IOException;
import java.net.http.HttpResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.pigihi.client.ClientCommunicator;
import com.pigihi.service.GETRequestSender;
import com.pigihi.service.UserQueryService;

/**
 * @author Ashish Sam T George
 *
 */
@Service
public class AdminShopQueryService implements UserQueryService {
	
	@Autowired
	private GETRequestSender getRequestSender;
	
	@Value("${shopService.uri}")
	private String shopUri;
	
	@Value("${shopService.endpoint.allShops}")
	private String allShopEndpoint;
	
	@Value("${shopService.endpoint.oneShop.queryParam}")
	private String oneShopQueryParam;
	
	@Override
	public String findAll(ClientCommunicator clientCommunicator) throws InterruptedException, IOException {
		String response = clientCommunicator.send();
		System.out.println("Response from shop service: " + response);
		return response;
	}

	@Override
	public String find(ClientCommunicator clientCommunicator) throws IOException, InterruptedException {
		String response = clientCommunicator.send();
		System.out.println("Response from shop service: " + response);
		return response;
	}

}
