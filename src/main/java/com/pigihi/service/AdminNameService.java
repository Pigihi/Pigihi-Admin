package com.pigihi.service;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pigihi.clients.ClientCommunicator;
import com.pigihi.entity.AdminEntity;
import com.pigihi.library.dataConverter.service.DataConverter;
import com.pigihi.repository.AdminRepository;

@Service
public class AdminNameService {
	
	@Autowired
	private AdminRepository adminRepository;
	
	@Autowired
	private DataConverter dataConverter;

	public String write(String email, String fullName) {
		AdminEntity adminEntity = adminRepository.findByEmail(email);
		adminEntity.setFullName(fullName);
		AdminEntity changedAdmin = adminRepository.save(adminEntity);
		String jsonString = dataConverter.convertToJson(changedAdmin);
		return jsonString;
	}

}
