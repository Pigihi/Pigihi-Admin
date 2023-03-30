package com.pigihi.service;

import java.io.IOException;

import com.pigihi.clients.ClientCommunicator;

public interface UserWriteService {
	public Object write(ClientCommunicator clientCommunicator) throws IOException, InterruptedException;
}
