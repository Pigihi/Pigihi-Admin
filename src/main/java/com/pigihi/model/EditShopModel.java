/**
 * 
 */
package com.pigihi.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * @author Ashish Sam T George
 *
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class EditShopModel extends UserModel {
	
	private String email;
	private String mobile;
	private String imageUrl;
	private String houseName;
	private String streetName;
	private String cityName;
	private String pincode;

}
