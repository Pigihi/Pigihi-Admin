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
import com.pigihi.library.dataConverter.service.DataConverter;
import com.pigihi.model.AdminModel;
import com.pigihi.repository.AdminRepository;

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
	private DataConverter dataConverter;
	
	public String addAdmin(AdminModel adminModel) throws IOException, InterruptedException {
		AdminEntity adminEntity = new AdminEntity();
		adminEntity.setEmail(adminModel.getEmail());
		adminEntity.setFullName(adminModel.getFullName());
		adminEntity.setImageUrl(adminModel.getImageUrl());
		adminEntity.setMobile(adminModel.getMobile());
		adminEntity.setRole(adminModel.getRole());
		//TODO How about enable status
		
		adminEntity = adminRepository.save(adminEntity);
		String jsonString = dataConverter.convertToJson(adminModel);
		HttpResponse<String> response = postRequestSender.send(authUri.concat(addAuthUserEndpoint), 
															jsonString);
		System.out.println("Response from Authentication Service: " + response.body());
		return response.body();
	}

}
