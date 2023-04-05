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
@EqualsAndHashCode(callSuper = true)
public class AdminModel extends UserModel{
	
	private String fullName;
	private String email;
	private String role;
	private String mobile;
	private String imageUrl;
	private String password;

}
