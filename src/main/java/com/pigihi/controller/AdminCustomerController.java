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

import com.pigihi.model.CustomerModel;
import com.pigihi.model.EditCustomerModel;
import com.pigihi.service.AdminCustomerServiceInterface;


/**
 * @author Ashish Sam T George
 *
 */
@RestController
@CrossOrigin("*")
@RequestMapping("/admin/customer")
public class AdminCustomerController {

	@Autowired
	private AdminCustomerServiceInterface adminCustomerService;

	@GetMapping("/all")
	public String getCustomers() throws IOException, InterruptedException {
		String customers = adminCustomerService.findAllCustomers();
		return customers;
	}

	@GetMapping
	public String customerInfo(@RequestParam String email) throws IOException, InterruptedException {
		String customer = adminCustomerService.findCustomer(email);
		return customer;
	}
	
	@PostMapping
	public String addCustomer(@RequestBody CustomerModel customerModel) throws IOException, InterruptedException {
		String customer = adminCustomerService.addCustomer(customerModel);
		return customer;
	}
	
	@PutMapping
	public String editCustomer(@RequestBody EditCustomerModel editCustomerModel) throws IOException, InterruptedException {
		String customer = adminCustomerService.editCustomer(editCustomerModel);
		return customer;
	}
	
	@PatchMapping
	public String enableCustomer(@RequestParam String email) throws IOException, InterruptedException {
		String customer = adminCustomerService.enableCustomer(email);
		return customer;
	}
	
	@DeleteMapping
	public String disableCustomer(@RequestParam String email) throws IOException, InterruptedException {
		String customer = adminCustomerService.disableCustomer(email);
		return customer;
	}

}
