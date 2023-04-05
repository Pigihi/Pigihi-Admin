
/**
 * 
 */
package com.pigihi.service.customer;


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
public class AdminCustomerQueryService implements UserQueryService {

	@Override
	public String findAll(ClientCommunicator clientCommunicator) throws InterruptedException, IOException {
		String response = clientCommunicator.send();
		System.out.println("Response from customer service: " + response);
		return response;
	}

	@Override
	public String find(ClientCommunicator clientCommunicator) throws IOException, InterruptedException {
		String response = clientCommunicator.send();
		System.out.println("Response from customer service: " + response);
		return response;
	}

}
