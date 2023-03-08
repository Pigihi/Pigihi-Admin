/**
 * 
 */
package com.pigihi.model;

import java.util.List;

import lombok.Data;

/**
 * @author Ashish Sam T George
 *
 */
@Data
public class CustomerModel {
	
//	private String id;
	private String fullName;
	private String email;
	private String role;
	private String password;
	private String mobile;
//	private String imageUrl;
	// Using string here rather than enum
//	private String enableStatus;
//	private String houseName;
//	private String streetName;
//	private String cityName;
//	private String pincode;
	// Using List<String> here rather than List<enum>
	private List<String> privileges;
	
}
