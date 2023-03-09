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
public class DELETERequestSender extends RESTRequestSender {

	@Override
	protected HttpResponse<String> send() throws IOException, InterruptedException {
		
		HttpClient httpClient = HttpClient.newHttpClient();
		HttpRequest deleteRequest = HttpRequest.newBuilder()
									.header("Content-Type", "application/json")
									.uri(this.uri)
									.DELETE()
									.build();
		HttpResponse<String> response = httpClient.send(deleteRequest, 
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
	
}
