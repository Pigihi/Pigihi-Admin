/**
 * 
 */
package com.pigihi.service;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpRequest.BodyPublishers;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import com.pigihi.model.CustomerModel;
import com.pigihi.model.EditCustomerModel;

/**
 * @author Ashish Sam T George
 *
 */
@Service
public class AdminCustomerService implements AdminCustomerServiceInterface {

	@Override
	public String findAllCustomers() throws InterruptedException, IOException {
		//TODO Call customer microservice for getting information of all customers
		HttpClient customerClient = HttpClient.newHttpClient();
		URI uri = URI.create("http://localhost:8091/user/customer/all");
//		URI uri = URI.create("http://customer-service:8091/user/customer/all");
		HttpRequest customerRequest = HttpRequest.newBuilder()
										.setHeader("Content-Type", "application/json")
										.uri(uri)
										.GET()
										.build();
		HttpResponse<String> response = customerClient.send(customerRequest,
										HttpResponse.BodyHandlers.ofString());
		System.out.println("Response from customer service: " + response.body());
		return response.body();
	}

	@Override
	public String findCustomer(String email) throws IOException, InterruptedException {
		//TODO Call customer microservice for getting information of a customer
		HttpClient customerClient = HttpClient.newHttpClient();
		URI uri = URI.create("http://localhost:8091/user/customer?email=" + email);
		HttpRequest customeRequest = HttpRequest.newBuilder()
										.setHeader("Content-Type", "application/json")
										.uri(uri)
										.GET()
										.build();
		HttpResponse<String> response = customerClient.send(customeRequest,
										HttpResponse.BodyHandlers.ofString());
		System.out.println("Response from customer service: " + response.body());
		return response.body();
		
	}

	@Override
	public String addCustomer(CustomerModel customerModel) throws IOException, InterruptedException {
		// TODO Call customer microservice for saving customer
		Gson gson = new Gson();
		String customerJson = gson.toJson(customerModel);
		HttpClient customerClient = HttpClient.newHttpClient();
//		URI uri = URI.create("http://localhost:8091/user/customer");
		URI uri = URI.create("http://localhost:8099/auth/register/user");
		HttpRequest customerRequest = HttpRequest.newBuilder()
										.setHeader("Content-Type", "application/json")
										.uri(uri)
										.POST(BodyPublishers.ofString(customerJson))
										.build();
		HttpResponse<String> response = customerClient.send(customerRequest, 
										HttpResponse.BodyHandlers.ofString());
		System.out.println("Response from Authentication Service: " + response.body());
		return response.body();
		
	}

	@Override
	public String enableCustomer(String email) throws IOException, InterruptedException {
		//TODO Call customer microservice for enabling customer
		HttpClient httpClient = HttpClient.newHttpClient();
		URI uri = URI.create("http://localhost:8091/user/customer/byAdmin?email=" + email);
		HttpRequest customeRequest = HttpRequest.newBuilder()
//										.setHeader("Content-Type", "application/json-patch+json")
										.uri(uri)
										.method("PATCH", BodyPublishers.ofString(""))
										.build();
		HttpResponse<String> response = httpClient.send(customeRequest, 
										HttpResponse.BodyHandlers.ofString());
		System.out.println("Response from customer service: " + response.body());
		
		return response.body();
		
	}

	@Override
	public String editCustomer(EditCustomerModel editCustomerModel) throws IOException, InterruptedException {
		// TODO Call customer microservice for editing customer
		Gson gson = new Gson();
		String customerJson = gson.toJson(editCustomerModel);
		HttpClient customerClient = HttpClient.newHttpClient();
		URI uri = URI.create("http://localhost:8091/user/customer");
		HttpRequest customerRequest = HttpRequest.newBuilder()
										.setHeader("Content-Type", "application/json")
										.uri(uri)
										.PUT(BodyPublishers.ofString(customerJson))
										.build();
		HttpResponse<String> response = customerClient.send(customerRequest, 
										HttpResponse.BodyHandlers.ofString());
		System.out.println("Response from Customer Service: " + response.body());
		return response.body();
		
	}

	@Override
	public String disableCustomer(String email) throws IOException, InterruptedException {
		// TODO Call customer microservice for disabling customer
		HttpClient httpClient = HttpClient.newHttpClient();
		URI uri = URI.create("http://localhost:8091/user/customer/byAdmin?email=" + email);
		HttpRequest customerRequest = HttpRequest.newBuilder()
										.uri(uri)
										.DELETE()
										.build();
		HttpResponse<String> response = httpClient.send(customerRequest, 
										HttpResponse.BodyHandlers.ofString());
		System.out.println("Response from customer service: " + response.body());
		
		return response.body();
	}

}
