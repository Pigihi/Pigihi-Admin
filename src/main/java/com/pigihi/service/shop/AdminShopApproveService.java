package com.pigihi.service.shop;

import java.io.IOException;

import com.pigihi.clients.ClientCommunicator;
import com.pigihi.service.UserWriteService;

public class AdminShopApproveService implements UserWriteService {

	@Override
	public String write(ClientCommunicator clientCommunicator) throws IOException, InterruptedException {
		String response = clientCommunicator.send();
		System.out.println("Response obtained from shop service: " + response);
		return response;
	}

}
