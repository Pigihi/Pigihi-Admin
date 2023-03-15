/**
 * 
 */
package com.pigihi.model;

import java.util.List;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author Ashish Sam T George
 *
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class ShopModel extends UserModel {
	
	private String fullName;
	private String email;
	private String role;
	private String password;
	private String mobile;
	
	private String idDocumentApproved;
	
	private List<String> privileges;

}
