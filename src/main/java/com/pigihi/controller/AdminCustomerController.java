/**
 * 
 */
package com.pigihi.controller;


import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
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

import com.pigihi.model.CustomerAddressModel;
import com.pigihi.model.CustomerModel;
import com.pigihi.model.EditCustomerModel;
import com.pigihi.service.customer.AdminCustomerAddService;
import com.pigihi.service.customer.AdminCustomerAddressService;
import com.pigihi.service.customer.AdminCustomerEditService;
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

	@Autowired
	private AdminCustomerQueryService adminCustomerFindService;
	
	@Autowired
	private AdminCustomerAddService adminCustomerAddService;
	
	@Autowired
	private AdminCustomerEditService adminCustomerEditService;
	
	@Autowired
	private AdminCustomerNameService adminCustomerNameService;
	
	@Autowired
	private AdminCustomerProfileImageService adminCustomerProfileImageService;
	
	@Autowired
	private AdminCustomerAddressService adminCustomerAddressService;
	
	@GetMapping("/all")
	public String getCustomers() throws IOException, InterruptedException {
		String customers = adminCustomerFindService.findAllCustomers();
		return customers;
	}

	@GetMapping
	public String customerInfo(@RequestParam String email) throws IOException, InterruptedException {
		//TODO Even if customer service is returning an error, the admin service returns a 200 OK status. Correct that
		String customer = adminCustomerFindService.findCustomer(email);
		return customer;
	}
	
	@PostMapping
	public String addCustomer(@RequestBody CustomerModel customerModel) throws IOException, InterruptedException {
		String customer = adminCustomerAddService.addCustomer(customerModel);
		return customer;
	}
	
	@PutMapping
	public String editCustomer(@RequestBody EditCustomerModel editCustomerModel) throws IOException, InterruptedException {
		String customer = adminCustomerEditService.editCustomer(editCustomerModel);
		return customer;
	}
	
	@PatchMapping
	public String enableCustomer(@RequestParam String email) throws IOException, InterruptedException {
		String customer = adminCustomerEditService.enableCustomer(email);
		return customer;
	}
	
	@DeleteMapping
	public String disableCustomer(@RequestParam String email) throws IOException, InterruptedException {
		String customer = adminCustomerEditService.disableCustomer(email);
		return customer;
	}
	
	@PutMapping("/fullName")
	public String changeCustomerName(@RequestParam String email,
										@RequestParam String fullName) throws IOException, InterruptedException {
		String customer = adminCustomerNameService.changeFullName(email, fullName);
		return customer;
	}
	
	@PutMapping("/profileImage")
	public String changeProfileImage(@RequestParam String email,
										@RequestParam String imageUrl) throws IOException, InterruptedException {
		String customer = adminCustomerProfileImageService.changeProfileImage(email, imageUrl);
		return customer;
	}
	
	public String changeAddress(@RequestParam String email,
								@RequestBody CustomerAddressModel customerAddressModel) throws IOException, InterruptedException {
		String customer = adminCustomerAddressService.changeAddress(email, customerAddressModel);
		return customer;
	}

}
