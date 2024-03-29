package com.pigihi.service;

import java.io.IOException;

import com.pigihi.client.ClientCommunicator;

public interface UserQueryService {
	
	public Object find(ClientCommunicator clientCommunicator) throws IOException, InterruptedException;
	
	public Object findAll(ClientCommunicator clientCommunicator) throws InterruptedException, IOException;

}
