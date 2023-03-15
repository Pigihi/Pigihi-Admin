/**
 * 
 */
package com.pigihi.service;

import java.io.IOException;
import java.net.http.HttpResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pigihi.entity.AdminEntity;
import com.pigihi.library.dataConverter.service.DataConverter;
import com.pigihi.repository.AdminRepository;

/**
 * @author Ashish Sam T George
 *
 */
@Service
public class AdminQueryService {
	
	@Autowired
	private AdminRepository adminRepository;
	
	@Autowired
	private DataConverter dataConverter;
	
	public String findAdmin(String email) throws IOException, InterruptedException {

		AdminEntity adminEntity = adminRepository.findByEmail(email);
		String adminJson = dataConverter.convertToJson(adminEntity);
		return adminJson;
		
	}

}
