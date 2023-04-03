package com.pigihi.service;

import java.io.IOException;

import com.pigihi.client.ClientCommunicator;

public interface UserWriteService {
	public Object write(ClientCommunicator clientCommunicator) throws IOException, InterruptedException;
}
