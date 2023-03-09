/**
 * 
 */
package com.pigihi.utility.request.rest;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpRequest.BodyPublishers;

import org.springframework.stereotype.Component;

/**
 * @author Ashish Sam T George
 *
 */
@Component
public class POSTRequestSender extends RESTRequestSender {

	@Override
	protected HttpResponse<String> send() throws IOException, InterruptedException {
		
		HttpClient httpClient = HttpClient.newHttpClient();
		HttpRequest postRequest = HttpRequest.newBuilder()
									.header("Content-Type", "application/json")
									.uri(this.uri)
									.POST(BodyPublishers.ofString(this.jsonBody))
									.build();
		HttpResponse<String> response = httpClient.send(postRequest, 
													HttpResponse.BodyHandlers.ofString());
		return response;
		
	}
	
	public HttpResponse<String> send(String uriString, String jsonString) throws IOException, InterruptedException {
		createUri(uriString);
		this.jsonBody = jsonString;
		HttpResponse<String> response = send();
		return response;
	}
	
	public HttpResponse<String> send(String uriString, String queryParam, String value, String jsonString) throws IOException, InterruptedException {
		createUri(uriString, queryParam, value);
		this.jsonBody = jsonString;
		HttpResponse<String> response = send();
		return response;
	}

}
