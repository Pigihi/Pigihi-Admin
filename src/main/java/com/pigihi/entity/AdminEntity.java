/**
 * 
 */
package com.pigihi.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.pigihi.model.UserModel;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author Ashish Sam T George
 *
 */
@Document(collection = "admin_collection")
@Data
@EqualsAndHashCode(callSuper = true)
public class AdminEntity extends UserModel {
	
	@Id
	private String id;
	private String fullName;
	private String email;
	private String role;
	private String mobile;
	private String imageUrl;

}
