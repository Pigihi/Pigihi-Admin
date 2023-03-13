/**
 * 
 */
package com.pigihi.service;

import java.io.IOException;
import java.net.http.HttpResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pigihi.entity.AdminEntity;
import com.pigihi.repository.AdminRepository;
import com.pigihi.utility.DataFormatter;
import com.pigihi.utility.request.rest.GETRequestSender;

/**
 * @author Ashish Sam T George
 *
 */
@Service
public class AdminFindService {
	
	@Autowired
	private AdminRepository adminRepository;
	
	@Autowired
	private DataFormatter dataFormatter;
	
	public String findAdmin(String email) throws IOException, InterruptedException {

		AdminEntity adminEntity = adminRepository.findByEmail(email);
		String adminJson = dataFormatter.convertToJson(adminEntity);
		return adminJson;
		
	}

}
