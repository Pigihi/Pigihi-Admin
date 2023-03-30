/**
 * 
 */
package com.pigihi.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.pigihi.clients.ClientCommunicator;
import com.pigihi.entity.AdminEntity;
import com.pigihi.model.AdminModel;
import com.pigihi.service.AdminAddService;
import com.pigihi.service.AdminNameService;
import com.pigihi.service.AdminProfileImageService;
import com.pigihi.service.AdminQueryService;

/**
 * Controller class for Admin
 * 
 * @author Ashish Sam T George
 *
 */
@RestController
@CrossOrigin("*")
@RequestMapping("/user/admin")
public class AdminController {
	
	@Autowired
	private AdminAddService adminAddService;
	
	@Autowired
	private AdminQueryService adminQueryService;
	
	@Autowired
	private AdminNameService adminNameService;
	
	@Autowired
	private AdminProfileImageService adminProfileImageService;
	
	/**
	 * Get information of an admin
	 * 
	 * @param email
	 * @return Json String - AdminEntity
	 * 
	 * @author Ashish Sam T George
	 * 
	 * @see AdminEntity
	 * 
	 */
	@GetMapping
	public String adminInfo(@RequestParam String email) {
		//TODO Get email from header
		String admin = adminQueryService.findAdmin(email);
		return admin;
	}
	
	/**
	 * Get information of all admins
	 * 
	 * @return Json String - List of AdminEntity
	 * 
	 * @author Ashish Sam T George
	 * 
	 * @see AdminEntity
	 * 
	 */
	@GetMapping("/all")
	public String getAllAdmins() {
		String admins = adminQueryService.findAllAdmins();
		return admins;
	}
	
	/**
	 * Add new admin
	 * 
	 * @param adminModel
	 * @return
	 * @throws InterruptedException
	 * @throws IOException
	 * 
	 * @author Ashish Sam T George
	 * 
	 * @see AdminEntity
	 * @see AdminModel
	 * 
	 */
	@PostMapping
	public String addAdmin(@RequestBody AdminModel adminModel) throws InterruptedException, IOException {
		String admin = adminAddService.addAdmin(adminModel);
		return admin;
		
	}
	
	/**
	 * Change full name of the admin
	 * 
	 * @param fullName
	 * @return Json String - AdminEntity
	 * 
	 * @author Ashish Sam T George
	 * 
	 * @see AdminEntity
	 * 
	 */
	@PutMapping("/fullName")
	public String changeAdminName(@RequestParam String fullName) {
		//TODO Get email from header
		
		String email = "";
		String admin = adminNameService.write(email, fullName);
		return admin;
	}
	
	/**
	 * Change profile image url
	 * 
	 * @param imageUrl
	 * @return Json String - AdminEntity
	 * 
	 * @author Ashish Sam T George
	 * 
	 * @see AdminEntity
	 * 
	 */
	@PutMapping("/profileImage")
	public String changeAdminProfileImage(@RequestParam String imageUrl) {
		//TODO Get email from header
		
		String email = "";
		String admin = adminProfileImageService.write(email, imageUrl);
		return admin;
	}

}
