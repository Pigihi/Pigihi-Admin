/**
 * 
 */
package com.pigihi.utility;

import org.springframework.stereotype.Component;

import com.google.gson.Gson;
import com.pigihi.model.CustomerModel;
import com.pigihi.model.EditCustomerModel;

/**
 * @author Ashish Sam T George
 *
 */
@Component
public class DataFormatter {
	
	public String convertToJson(CustomerModel customerModel) {
		Gson gson = new Gson();
		String jsonString = gson.toJson(customerModel);
		return jsonString;
	}
	
	public String convertToJson(EditCustomerModel editCustomerModel) {
		Gson gson = new Gson();
		String jsonString = gson.toJson(editCustomerModel);
		return jsonString;
	}

}
