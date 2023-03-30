/**
 * 
 */
package com.pigihi.controller;


import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.pigihi.clients.ClientCommunicator;
import com.pigihi.clients.auth.AuthPOSTCommunicator;
import com.pigihi.clients.customer.CustomerDELETECommunicator;
import com.pigihi.clients.customer.CustomerGETCommunicator;
import com.pigihi.clients.customer.CustomerPATCHCommunicator;
import com.pigihi.clients.customer.CustomerPOSTCommunicator;
import com.pigihi.clients.customer.CustomerPUTCommunicator;
import com.pigihi.library.dataConverter.service.DataConverter;
import com.pigihi.model.CustomerAddressModel;
import com.pigihi.model.CustomerModel;
import com.pigihi.model.EditCustomerModel;
import com.pigihi.service.customer.AdminCustomerAddService;
import com.pigihi.service.customer.AdminCustomerAddressService;
import com.pigihi.service.customer.AdminCustomerStatusService;
import com.pigihi.service.customer.AdminCustomerNameService;
import com.pigihi.service.customer.AdminCustomerProfileImageService;
import com.pigihi.service.customer.AdminCustomerQueryService;


/**
 * @author Ashish Sam T George
 *
 */
@RestController
@CrossOrigin("*")
@RequestMapping("/admin/customer")
public class AdminCustomerController {

	private ClientCommunicator clientCommunicator;

	@Autowired
	private AdminCustomerQueryService adminCustomerFindService;

	@Autowired
	private AdminCustomerAddService adminCustomerAddService;

	@Autowired
	private AdminCustomerStatusService adminCustomerStatusService;

	@Autowired
	private AdminCustomerNameService adminCustomerNameService;

	@Autowired
	private AdminCustomerProfileImageService adminCustomerProfileImageService;

	@Autowired
	private AdminCustomerAddressService adminCustomerAddressService;

	@Autowired
	private DataConverter dataConverter;

	@Value("${customerService.endpoint.allCustomers}")
	private String allCustomerEndpoint;

	@Value("${customerService.endpoint.oneCustomer.queryParam}")
	private String oneCustomerQueryParam;

	@Value("${customerService.endpoint.admin.endpoint}")
	private String customerAdminEndpoint;

	@Value("${customerService.endpoint.admin.address.endpoint}")
	private String customerAdminAddressEndpoint;

	@Value("${customerService.endpoint.admin.address.queryParam}")
	private String customerAdminAddressQueryParam;

	@Value("${customerService.endpoint.admin.fullName.endpoint}")
	private String customerNameEndpoint;

	@Value("${customerService.endpoint.admin.fullName.queryParam1}")
	private String customerNameQueryParam1;

	@Value("${customerService.endpoint.admin.fullName.queryParam2}")
	private String customerNameQueryParam2;

	@Value("${customerService.endpoint.admin.profileImage.endpoint}")
	private String customerProfileImageEndpoint;

	@Value("${customerService.endpoint.admin.profileImage.queryParam1}")
	private String customerProfileImageQueryParam1;

	@Value("${customerService.endpoint.admin.profileImage.queryParam2}")
	private String customerProfileImageQueryParam2;

	@Value("${customerService.endpoint.enableCustomer.endpoint}")
	private String enableCustomerEndpoint;

	@Value("${customerService.endpoint.enableCustomer.queryParam}")
	private String enableCustomerQueryParam;

	@Value("${customerService.endpoint.disableCustomer.endpoint}")
	private String disableCustomerEndpoint;

	@Value("${customerService.endpoint.disableCustomer.queryParam}")
	private String disableCustomerQueryParam;

	@Value("${authService.endpoint.addUser}")
	private String addAuthUserEndpoint;

	@GetMapping("/all")
	public String getCustomers() throws IOException, InterruptedException {
		clientCommunicator = new CustomerGETCommunicator(allCustomerEndpoint);
		String customers = adminCustomerFindService.findAll(clientCommunicator);
		return customers;
	}

	@GetMapping
	public String customerInfo(@RequestParam String email) throws IOException, InterruptedException {

		// TODO Even if customer service is returning an error, the admin service
		// returns a 200 OK status. Correct that
		clientCommunicator = new CustomerGETCommunicator("", oneCustomerQueryParam, email);
		String customer = adminCustomerFindService.find(clientCommunicator);
		return customer;
	}

	@PostMapping
	public String addCustomer(@RequestBody CustomerModel customerModel) throws IOException, InterruptedException {
		String jsonBody = dataConverter.convertToJson(customerModel);
		clientCommunicator = new AuthPOSTCommunicator(addAuthUserEndpoint, jsonBody);
		String customer = adminCustomerAddService.write(clientCommunicator);
		return customer;
	}

//	@PutMapping
//	public String editCustomer(@RequestBody EditCustomerModel editCustomerModel)
//			throws IOException, InterruptedException {
//		String customer = adminCustomerEditService.editCustomer(editCustomerModel);
//		return customer;
//	}

	@PatchMapping
	public String enableCustomer(@RequestParam String email) throws IOException, InterruptedException {
		clientCommunicator = new CustomerPATCHCommunicator(enableCustomerEndpoint, enableCustomerQueryParam, email);
		String customer = adminCustomerStatusService.write(clientCommunicator);
		return customer;
	}

	@DeleteMapping
	public String disableCustomer(@RequestParam String email) throws IOException, InterruptedException {
		clientCommunicator = new CustomerDELETECommunicator(disableCustomerEndpoint, disableCustomerQueryParam, email);
		String customer = adminCustomerStatusService.write(clientCommunicator);
		return customer;
	}

	@PutMapping("/fullName")
	public String changeCustomerName(@RequestParam String email, @RequestParam String fullName)
			throws IOException, InterruptedException {
		String endpoint = customerAdminEndpoint.concat(customerNameEndpoint);
		clientCommunicator = new CustomerPUTCommunicator(endpoint, customerNameQueryParam1, email,
				customerNameQueryParam2, fullName);
		String customer = adminCustomerNameService.write(clientCommunicator);
		return customer;
	}

	@PutMapping("/profileImage")
	public String changeProfileImage(@RequestParam String email, @RequestParam String imageUrl)
			throws IOException, InterruptedException {
		String endpoint = customerAdminEndpoint.concat(customerProfileImageEndpoint);
		clientCommunicator = new CustomerPUTCommunicator(endpoint, customerProfileImageQueryParam1, email,
				customerProfileImageQueryParam2, imageUrl);
		String customer = adminCustomerProfileImageService.write(clientCommunicator);
		return customer;
	}

	@PutMapping("/address")
	public String changeAddress(@RequestParam String email, @RequestBody CustomerAddressModel customerAddressModel)
			throws IOException, InterruptedException {
		String endpoint = customerAdminEndpoint.concat(customerAdminAddressEndpoint);
		String jsonBody = dataConverter.convertToJson(customerAddressModel);
		clientCommunicator = new CustomerPUTCommunicator(endpoint, customerAdminAddressQueryParam, email, jsonBody);
		String customer = adminCustomerAddressService.write(clientCommunicator);
		return customer;
	}

}
