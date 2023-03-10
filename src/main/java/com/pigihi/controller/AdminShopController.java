/**
 * 
 */
package com.pigihi.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.pigihi.model.CustomerModel;
import com.pigihi.model.EditCustomerModel;
import com.pigihi.model.EditShopModel;
import com.pigihi.model.ShopModel;
import com.pigihi.service.AdminShopAddService;
import com.pigihi.service.AdminShopEditService;
import com.pigihi.service.AdminShopFindService;

/**
 * @author Ashish Sam T George
 *
 */
@RestController
@CrossOrigin("*")
@RequestMapping("/admin/shop")
public class AdminShopController {
	
	@Autowired
	private AdminShopFindService adminShopFindService;
	
	@Autowired
	private AdminShopAddService adminShopAddService;
	
	@Autowired
	private AdminShopEditService adminShopEditService;
	
	@GetMapping("/all")
	public String getCustomers() throws IOException, InterruptedException {
		String shops = adminShopFindService.findAllShops();
		return shops;
	}

	@GetMapping
	public String shopInfo(@RequestParam String email) throws IOException, InterruptedException {
		//TODO Even if customer service is returning an error, the admin service returns a 200 OK status. Correct that
		String shop = adminShopFindService.findShop(email);
		return shop;
	}
	
	@PostMapping
	public String addShop(@RequestBody ShopModel shopModel) throws IOException, InterruptedException {
		String shop = adminShopAddService.addShop(shopModel);
		return shop;
	}
	
	@PutMapping
	public String editShop(@RequestBody EditShopModel editShopModel) throws IOException, InterruptedException {
		String shop = adminShopEditService.editShop(editShopModel);
		return shop;
	}
	
	@PatchMapping
	public String enableShop(@RequestParam String email) throws IOException, InterruptedException {
		String shop = adminShopEditService.enableShop(email);
		return shop;
	}
	
	@DeleteMapping
	public String disableShop(@RequestParam String email) throws IOException, InterruptedException {
		String shop = adminShopEditService.disableShop(email);
		return shop;
	}
	
	@PatchMapping("/approve")
	public String approveShop(@RequestParam String email) {
		return "Not Implemented";
	}
	
}
