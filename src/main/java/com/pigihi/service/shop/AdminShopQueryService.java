/**
 * 
 */
package com.pigihi.service.shop;

import java.io.IOException;
import java.net.http.HttpResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.pigihi.service.GETRequestSender;

/**
 * @author Ashish Sam T George
 *
 */
@Service
public class AdminShopQueryService {
	
	@Autowired
	private GETRequestSender getRequestSender;
	
	@Value("${shopService.uri}")
	private String shopUri;
	
	@Value("${shopService.endpoint.allShops}")
	private String allShopEndpoint;
	
	@Value("${shopService.endpoint.oneShop.queryParam}")
	private String oneShopQueryParam;
	
	public String findAllShops() throws InterruptedException, IOException {

		HttpResponse<String> response = getRequestSender.send(
															shopUri.concat(allShopEndpoint));
		System.out.println("Response from customer service: " + response.body());
		return response.body();
		
	}

	public String findShop(String email) throws IOException, InterruptedException {

		HttpResponse<String> response = getRequestSender.send(shopUri, 
																oneShopQueryParam, email);
		System.out.println("Response from customer service: " + response.body());
		return response.body();
		
	}

}
