/**
 * 
 */
package com.pigihi.utility.request.rest;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpResponse;

import lombok.Getter;
import lombok.Setter;

/**
 * @author Ashish Sam T George
 *
 */
public abstract class RESTRequestSender {
	
	protected URI uri;
	protected String jsonBody = "";
	
	protected void createUri(String uriString, String queryString, String value) {
		URI uri = URI.create(uriString.concat("?")
							.concat(queryString)
							.concat("=")
							.concat(value));
		this.uri = uri;
	}
	
	protected void createUri(String uriString) {
		URI uri = URI.create(uriString);
		this.uri = uri;
	}
	
	protected abstract HttpResponse<String> send() throws IOException, InterruptedException;

}
