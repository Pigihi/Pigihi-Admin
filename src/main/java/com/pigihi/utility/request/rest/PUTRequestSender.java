/**
 * 
 */
package com.pigihi.utility.request.rest;

import java.io.IOException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpRequest.BodyPublishers;

/**
 * @author Ashish Sam T George
 *
 */
public class PUTRequestSender extends RESTRequestSender {

	@Override
	protected HttpResponse<String> send() throws IOException, InterruptedException {
		
		HttpClient httpClient = HttpClient.newHttpClient();
		HttpRequest putRequest = HttpRequest.newBuilder()
									.header("Content-Type", "application/json")
									.uri(this.uri)
									.PUT(BodyPublishers.ofString(this.jsonBody))
									.build();
		HttpResponse<String> response = httpClient.send(putRequest, 
													HttpResponse.BodyHandlers.ofString());
		return response;
		
	}
	
	public HttpResponse<String> send(String uriString) throws IOException, InterruptedException {
		createUri(uriString);
		HttpResponse<String> response = send();
		return response;
	}
	
	public HttpResponse<String> send(String uriString, String queryString, String value) throws IOException, InterruptedException {
		createUri(uriString, queryString, value);
		HttpResponse<String> response = send();
		return response;
	}
	
	public HttpResponse<String> send(String uriString, String jsonString) throws IOException, InterruptedException {
		createUri(uriString);
		this.jsonBody = jsonString;
		HttpResponse<String> response = send();
		return response;
	}

}
