/**
 * 
 */
package com.pigihi.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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

import com.pigihi.client.ClientCommunicator;
import com.pigihi.client.auth.AuthPOSTCommunicator;
import com.pigihi.client.customer.CustomerDELETECommunicator;
import com.pigihi.client.customer.CustomerPATCHCommunicator;
import com.pigihi.client.customer.CustomerPUTCommunicator;
import com.pigihi.client.shop.ShopGETCommunicator;
import com.pigihi.client.shop.ShopPATCHCommunicator;
import com.pigihi.library.dataConverter.service.DataConverter;
import com.pigihi.model.CustomerModel;
import com.pigihi.model.EditCustomerModel;
import com.pigihi.model.EditShopModel;
import com.pigihi.model.ShopModel;
import com.pigihi.service.shop.AdminShopAddService;
import com.pigihi.service.shop.AdminShopApproveService;
import com.pigihi.service.shop.AdminShopNameService;
import com.pigihi.service.shop.AdminShopProfileImageService;
import com.pigihi.service.shop.AdminShopQueryService;
import com.pigihi.service.shop.AdminShopStatusService;

/**
 * @author Ashish Sam T George
 *
 */
@RestController
@CrossOrigin("*")
@RequestMapping("/admin/shop")
public class AdminShopController {
	
	private ClientCommunicator clientCommunicator;
	
	@Autowired
	private AdminShopQueryService adminShopFindService;
	
	@Autowired
	private AdminShopAddService adminShopAddService;
	
	@Autowired
	private AdminShopStatusService adminShopStatusService;
	
	@Autowired
	private AdminShopNameService adminShopNameService;
	
	@Autowired
	private AdminShopProfileImageService adminShopProfileImageService;
	
	@Autowired
	private AdminShopApproveService adminShopApproveService;
	
	@Autowired
	private DataConverter dataConverter;
	
	@Value("${shopService.endpoint.allShops}")
	private String allShopEndpoint;
	
	@Value("${shopService.endpoint.oneShop.queryParam}")
	private String oneShopQueryParam;
	
	@Value("${authService.endpoint.addUser}")
	private String addAuthUserEndpoint;
	
	@Value("${shopService.endpoint.enableCustomer.endpoint}")
	private String enableShopEndpoint;
	
	@Value("${shopService.endpoint.enableCustomer.queryParam}")
	private String enableShopQueryParam;
	
	@Value("${shopService.endpoint.disableCustomer.endpoint}")
	private String disableShopEndpoint;
	
	@Value("${shopService.endpoint.disableCustomer.queryParam}")
	private String disableShopQueryParam;
	
	@Value("${shopService.endpoint.admin.endpoint}")
	private String shopAdminEndpoint;
	
	@Value("${shopService.endpoint.admin.fullName.endpoint}")
	private String shopNameEndpoint;

	@Value("${shopService.endpoint.admin.fullName.queryParam1}")
	private String shopNameQueryParam1;

	@Value("${shopService.endpoint.admin.fullName.queryParam2}")
	private String shopNameQueryParam2;
	
	@Value("${shopService.endpoint.admin.profileImage.endpoint}")
	private String shopProfileImageEndpoint;

	@Value("${shopService.endpoint.admin.profileImage.queryParam1}")
	private String shopProfileImageQueryParam1;

	@Value("${shopService.endpoint.admin.profileImage.queryParam2}")
	private String shopProfileImageQueryParam2;
	
	@Value("${shopService.endpoint.admin.approve.endpoint}")
	private String shopApproveEndpoint;

	@Value("${shopService.endpoint.admin.approve.queryParam}")
	private String shopApproveQueryParam;
	
	@Value("${shopService.endpoint.admin.disapprove.endpoint}")
	private String shopDisapproveEndpoint;

	@Value("${shopService.endpoint.admin.disapprove.queryParam}")
	private String shopDisapproveQueryParam;
	
	@GetMapping("/all")
	public String getShops() throws IOException, InterruptedException {
		clientCommunicator = new ShopGETCommunicator(allShopEndpoint);
		String shops = adminShopFindService.findAll(clientCommunicator);
		return shops;
	}

	@GetMapping
	public String shopInfo(@RequestParam String email) throws IOException, InterruptedException {
		//TODO Even if customer service is returning an error, the admin service returns a 200 OK status. Correct that
		clientCommunicator = new ShopGETCommunicator("", oneShopQueryParam, email);
		String shop = adminShopFindService.find(clientCommunicator);
		return shop;
	}
	
	@PostMapping
	public String addShop(@RequestBody ShopModel shopModel) throws IOException, InterruptedException {
		String jsonBody = dataConverter.convertToJson(shopModel);
		clientCommunicator = new AuthPOSTCommunicator(addAuthUserEndpoint, jsonBody);
		String shop = adminShopAddService.write(clientCommunicator);
		return shop;
	}
	
	@PatchMapping
	public String enableShop(@RequestParam String email) throws IOException, InterruptedException {
		clientCommunicator = new CustomerPATCHCommunicator(enableShopEndpoint, enableShopQueryParam, email);
		String shop = adminShopStatusService.write(clientCommunicator);
		return shop;
	}
	
	@DeleteMapping
	public String disableShop(@RequestParam String email) throws IOException, InterruptedException {
		clientCommunicator = new CustomerDELETECommunicator(disableShopEndpoint, disableShopQueryParam, email);
		String shop = adminShopStatusService.write(clientCommunicator);
		return shop;
	}
	
	@PutMapping("/fullName")
	public String changeShopName(@RequestParam String email, @RequestParam String fullName) throws IOException, InterruptedException {
		String endpoint = shopAdminEndpoint.concat(shopNameEndpoint);
		clientCommunicator = new CustomerPUTCommunicator(endpoint, shopNameQueryParam1, email,
				shopNameQueryParam2, fullName);
		String customer = adminShopNameService.write(clientCommunicator);
		return customer;
	}
	
	@PutMapping("/profileImage")
	public String changeProfileImage(@RequestParam String email, @RequestParam String imageUrl) throws IOException, InterruptedException {
		String endpoint = shopAdminEndpoint.concat(shopProfileImageEndpoint);
		clientCommunicator = new CustomerPUTCommunicator(endpoint, shopProfileImageQueryParam1, email,
				shopProfileImageQueryParam2, imageUrl);
		String customer = adminShopProfileImageService.write(clientCommunicator);
		return customer;
	}
	
	@PatchMapping("/approve")
	public String approveShop(@RequestParam String email) throws IOException, InterruptedException {
		String endpoint = shopAdminEndpoint.concat(shopApproveEndpoint);
		clientCommunicator = new ShopPATCHCommunicator(endpoint, shopApproveQueryParam, email);
		String shop = adminShopApproveService.write(clientCommunicator);
		return shop;
	}
	
	@DeleteMapping("/disapprove")
	public String disapproveShop(@RequestParam String email) throws IOException, InterruptedException {
		String endpoint = shopAdminEndpoint.concat(shopDisapproveEndpoint);
		clientCommunicator = new ShopPATCHCommunicator(endpoint, shopDisapproveQueryParam, email);
		String shop = adminShopApproveService.write(clientCommunicator);
		return shop;
	}
	
}
