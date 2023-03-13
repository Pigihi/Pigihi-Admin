/**
 * 
 */
package com.pigihi.service;

import java.io.IOException;
import java.net.http.HttpResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.pigihi.entity.AdminEntity;
import com.pigihi.entity.AdminModel;
import com.pigihi.repository.AdminRepository;
import com.pigihi.utility.DataFormatter;
import com.pigihi.utility.request.rest.POSTRequestSender;

/**
 * @author Ashish Sam T George
 *
 */
@Service
public class AdminAddService {
	
	@Autowired
	private AdminRepository adminRepository;
	
	@Autowired
	private POSTRequestSender postRequestSender;
	
	@Value("${authService.uri}")
	private String authUri;
	
	@Value("${authService.endpoint.addUser}")
	private String addAuthUserEndpoint;
	
	@Autowired
	private DataFormatter dataFormatter;
	
	public String addAdmin(AdminModel adminModel) throws IOException, InterruptedException {
		
		AdminEntity adminEntity = new AdminEntity();
		adminEntity.setEmail(adminModel.getEmail());
		adminEntity.setFullName(adminModel.getFullName());
		adminEntity.setImageUrl(adminModel.getImageUrl());
		adminEntity.setMobile(adminModel.getMobile());
		adminEntity.setRole(adminModel.getRole());
		
		adminEntity = adminRepository.save(adminEntity);
		String jsonString = dataFormatter.convertToJson(adminModel);
		HttpResponse<String> response = postRequestSender.send(authUri.concat(addAuthUserEndpoint), 
															jsonString);
		System.out.println("Response from Authentication Service: " + response.body());
		return response.body();
		
	}

}
