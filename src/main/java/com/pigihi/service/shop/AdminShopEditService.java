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
import com.pigihi.model.EditCustomerModel;
import com.pigihi.model.EditShopModel;
import com.pigihi.service.DELETERequestSender;
import com.pigihi.service.PATCHRequestSender;
import com.pigihi.service.PUTRequestSender;

/**
 * @author Ashish Sam T George
 *
 */
@Service
public class AdminShopEditService {
	
	@Autowired
	private PUTRequestSender putRequestSender;
	
	@Autowired
	private PATCHRequestSender patchRequestSender;
	
	@Autowired
	private DELETERequestSender deleteRequestSender;
	
	@Autowired
	private DataConverter dataConverter;
	
	@Value("${shopService.uri}")
	private String shopUri;
	
	@Value("${shopService.endpoint.enableCustomer.endpoint}")
	private String enableShopEndpoint;
	
	@Value("${shopService.endpoint.enableCustomer.queryParam}")
	private String enableShopQueryParam;
	
	@Value("${shopService.endpoint.disableCustomer.endpoint}")
	private String disableShopEndpoint;
	
	@Value("${shopService.endpoint.disableCustomer.queryParam}")
	private String disableShopQueryParam;
	
	public String editShop(EditShopModel editShopModel) throws IOException, InterruptedException {

		String jsonString = dataConverter.convertToJson(editShopModel);
		HttpResponse<String> response = putRequestSender.send(shopUri, jsonString);
		System.out.println("Response from Shop Service: " + response.body());
		return response.body();
		
	}

	public String enableShop(String email) throws IOException, InterruptedException {
		
		String uri = shopUri.concat(enableShopEndpoint);
		HttpResponse<String> response = patchRequestSender.send(uri, 
															enableShopQueryParam, email);
		System.out.println("Response from shop service: " + response.body());
		return response.body();
		
	}

	public String disableShop(String email) throws IOException, InterruptedException {
		
		String uri = shopUri.concat(disableShopEndpoint);
		HttpResponse<String> response = deleteRequestSender.send(uri, 
											disableShopQueryParam, email);
		System.out.println("Response from shop service: " + response.body());
		
		return response.body();
	}

}
