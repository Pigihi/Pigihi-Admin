/**
 * 
 */
package com.pigihi.service;

import java.io.IOException;
import java.net.http.HttpResponse;
import java.util.List;

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
	
	public String findAdmin(String email) {
		AdminEntity adminEntity = adminRepository.findByEmail(email);
		String adminJson = dataConverter.convertToJson(adminEntity);
		return adminJson;
		
	}
	
	public String findAllAdmins() {
		List<AdminEntity> adminEntities = adminRepository.findAll();
		String adminJson = dataConverter.convertToJson(adminEntities);
		return adminJson;
	}

}
