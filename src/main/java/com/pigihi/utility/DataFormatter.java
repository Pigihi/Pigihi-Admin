/**
 * 
 */
package com.pigihi.utility;

import org.springframework.stereotype.Component;

import com.google.gson.Gson;
import com.pigihi.model.CustomerModel;
import com.pigihi.model.EditCustomerModel;
import com.pigihi.model.EditShopModel;
import com.pigihi.model.ShopModel;
import com.pigihi.model.UserModel;

/**
 * @author Ashish Sam T George
 *
 */
@Component
public class DataFormatter {
	
	public String convertToJson(UserModel userModel) {
		Gson gson = new Gson();
		String jsonString = gson.toJson(userModel);
		return jsonString;
	}
	
//	public String convertToJson(CustomerModel customerModel) {
//		Gson gson = new Gson();
//		String jsonString = gson.toJson(customerModel);
//		return jsonString;
//	}
//	
//	public String convertToJson(EditCustomerModel editCustomerModel) {
//		Gson gson = new Gson();
//		String jsonString = gson.toJson(editCustomerModel);
//		return jsonString;
//	}

}
