/**
 * 
 */
package com.pigihi.service;

import java.io.IOException;
import java.util.List;

import com.pigihi.model.CustomerModel;
import com.pigihi.model.EditCustomerModel;

/**
 * @author Ashish Sam T George
 *
 */
public interface AdminCustomerServiceInterface {

	String findAllCustomers() throws InterruptedException, IOException;

	String findCustomer(String email) throws IOException, InterruptedException;

	String addCustomer(CustomerModel customerModel) throws IOException, InterruptedException;

	String enableCustomer(String email) throws IOException, InterruptedException;

	String editCustomer(EditCustomerModel editCustomerModel) throws IOException, InterruptedException;

	String disableCustomer(String email) throws IOException, InterruptedException;

}
