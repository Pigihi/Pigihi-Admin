package com.pigihi.service.customer;

import java.io.IOException;
import java.net.http.HttpResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.pigihi.clients.ClientCommunicator;
import com.pigihi.service.PUTRequestSender;
import com.pigihi.service.UserWriteService;

@Service
public class AdminCustomerProfileImageService implements UserWriteService {

	@Override
	public String write(ClientCommunicator clientCommunicator) throws IOException, InterruptedException {
		String response = clientCommunicator.send();
		System.out.println("Response obtained from customer service: " + response);
		return response;
	}

}
