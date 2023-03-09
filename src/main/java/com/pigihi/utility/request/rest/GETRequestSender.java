/**
 * 
 */
package com.pigihi.utility.request.rest;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;

import org.springframework.stereotype.Component;

/**
 * @author Ashish Sam T George
 *
 */
@Component
public class GETRequestSender extends RESTRequestSender {
	
	@Override
	protected HttpResponse<String> send() throws IOException, InterruptedException{
		HttpClient httpClient = HttpClient.newHttpClient();
		HttpRequest getRequest = HttpRequest.newBuilder()
									.uri(this.uri)
									.GET()
									.build();
		HttpResponse<String> response = httpClient.send(getRequest, 
														HttpResponse.BodyHandlers.ofString());
		return response;
	}
	
	public HttpResponse<String> send(String uriString) throws IOException, InterruptedException{ 
		createUri(uriString);
		HttpResponse<String> response = send();
		return response;
	}
	
	public HttpResponse<String> send(String uriString, String queryString, String value) throws IOException, InterruptedException {
		createUri(uriString, queryString, value);
		HttpResponse<String> response = send();
		return response;
	}

}
