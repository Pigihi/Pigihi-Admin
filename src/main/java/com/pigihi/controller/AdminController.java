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

import com.pigihi.entity.AdminEntity;
import com.pigihi.entity.AdminModel;
import com.pigihi.service.AdminAddService;
import com.pigihi.service.AdminFindService;

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
	private AdminFindService adminFindService;
	
	@GetMapping
	public String adminInfo(@RequestParam String email) throws IOException, InterruptedException {
		String admin = adminFindService.findAdmin(email);
		return admin;
	}
	
	@PostMapping
	public String addAdmin(@RequestBody AdminModel adminModel) throws InterruptedException, IOException {
		
		String admin = adminAddService.addAdmin(adminModel);
		return admin;
		
	}
	
//	@PutMapping
//	public String editAdmin(@RequestBody )

}
