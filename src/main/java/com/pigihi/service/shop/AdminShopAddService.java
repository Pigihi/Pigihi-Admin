/**
 * 
 */
package com.pigihi.service.shop;

import java.io.IOException;
import java.net.http.HttpResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.pigihi.library.dataConverter.service.DataConverter;
import com.pigihi.model.CustomerModel;
import com.pigihi.model.ShopModel;
import com.pigihi.service.POSTRequestSender;

/**
 * @author Ashish Sam T George
 *
 */
@Service
public class AdminShopAddService {
	
	@Autowired
	private POSTRequestSender postRequestSender;
	
	@Autowired
	private DataConverter dataConverter;
	
	@Value("${authService.uri}")
	private String authUri;
	
	@Value("${authService.endpoint.addUser}")
	private String addAuthUserEndpoint;
	
	public String addShop(ShopModel shopModel) throws IOException, InterruptedException {

		String jsonString = dataConverter.convertToJson(shopModel);
		HttpResponse<String> response = postRequestSender.send(authUri.concat(addAuthUserEndpoint), 
															jsonString);
		System.out.println("Response from Authentication Service: " + response.body());
		return response.body();

	}

}
