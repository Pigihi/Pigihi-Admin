package com.pigihi.service.shop;

import java.io.IOException;

import org.springframework.stereotype.Service;

import com.pigihi.clients.ClientCommunicator;
import com.pigihi.service.UserWriteService;

@Service
public class AdminShopStatusService implements UserWriteService {
	
	@Override
	public String write(ClientCommunicator clientCommunicator) throws IOException, InterruptedException {
		String response = clientCommunicator.send();
		System.out.println("Response from customer service: " + response);
		return response;
	}
	
}
