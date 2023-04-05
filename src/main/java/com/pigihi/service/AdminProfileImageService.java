package com.pigihi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pigihi.entity.AdminEntity;
import com.pigihi.library.dataConverter.service.DataConverter;
import com.pigihi.repository.AdminRepository;


@Service
public class AdminProfileImageService {
	
	@Autowired
	private AdminRepository adminRepository;
	
	@Autowired
	private DataConverter dataConverter;

	public String write(String email, String imageUrl) {
		AdminEntity adminEntity = adminRepository.findByEmail(email);
		adminEntity.setImageUrl(imageUrl);
		AdminEntity changedAdmin = adminRepository.save(adminEntity);
		String jsonString = dataConverter.convertToJson(changedAdmin);
		return jsonString;
	}

}
