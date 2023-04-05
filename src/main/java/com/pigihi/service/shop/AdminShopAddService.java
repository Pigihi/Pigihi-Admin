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
import com.pigihi.library.dataConverter.service.DataConverter;
import com.pigihi.model.CustomerModel;
import com.pigihi.model.ShopModel;
import com.pigihi.service.POSTRequestSender;
import com.pigihi.service.UserWriteService;

/**
 * @author Ashish Sam T George
 *
 */
@Service
public class AdminShopAddService implements UserWriteService {
	
	@Override
	public String write(ClientCommunicator clientCommunicator) throws IOException, InterruptedException {
		String response = clientCommunicator.send();
		System.out.println("Response from Authentication Service: " + response);
		return response;
	}

}
