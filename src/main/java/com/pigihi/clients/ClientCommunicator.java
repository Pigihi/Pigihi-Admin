package com.pigihi.clients;

import java.io.IOException;
import java.net.http.HttpResponse;

import org.springframework.beans.factory.annotation.Value;

public interface ClientCommunicator {

	String send() throws IOException, InterruptedException;

}
