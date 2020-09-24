package com.justinsenia.ally_orchestration_service.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.justinsenia.ally_orchestration_service.bean.UserBean;
import com.justinsenia.ally_orchestration_service.proxy.UserServiceProxy;


@RequestMapping(path="/api/v1")
@RestController
public class UserController {

	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	// Use this method for multiple urlVariables
	// -----------------------------
	//@GetMapping("/users/{id}")
	//public UserBean getUserById(@PathVariable String username, @PathVariable String password) {
	//	
	//	
	//	Map<String, String> uriVariables = new HashMap<>();
	//	uriVariables.put("username", username);
	//	uriVariables.put("password", password);
	//	
	//	ResponseEntity<UserBean> responseEntity = new RestTemplate().getForEntity(
	//		"http://localhost:8100/users/{id}", UserBean.class, uriVariables);
		
	// Use this method for single urlVariables
	@GetMapping("/updateuserwithid/{id}")
	public UserBean updateUsernameAppendId(@PathVariable(value = "id") Long userId) {
		
		Map<String, Long> uriVariables = new HashMap<>();
		uriVariables.put("id", userId);
			
		ResponseEntity<UserBean> responseEntity = new RestTemplate().getForEntity(
				"http://localhost:8100/api/v1/users/{id}", UserBean.class, uriVariables);
		
		UserBean response = responseEntity.getBody();
		
		return new UserBean(userId, response.getUsername() + userId, response.getPassword(),
			response.getEmailAddress(), response.getCreatedAt(), response.getCreatedBy(),
			response.getUpdatedAt(), response.getUpdatedBy());
		
		//return new UserBean(userId, response.getUsername(), response.getPassword(),
		//		response.getEmailAddress(), response.getCreatedAt(), response.getCreatedBy(),
		//		response.getUpdatedAt(), response.getUpdatedBy(), response.getPort());
		
	}

	@Autowired
	private UserServiceProxy proxy;

	@GetMapping("/updateuserwithid-feign/{id}")
	public UserBean updateUsernameAppendIdFeign(@PathVariable(value = "id") Long userId) {

	    UserBean response = proxy.getUserById(userId);

	    logger.info("{}", response);

	    return new UserBean(userId, response.getUsername() + userId, response.getPassword(),
				response.getEmailAddress(), response.getCreatedAt(), response.getCreatedBy(),
				response.getUpdatedAt(), response.getUpdatedBy());
	    
	
		//return new UserBean(userId, response.getUsername(), response.getPassword(),
		//		response.getEmailAddress(), response.getCreatedAt(), response.getCreatedBy(),
		//		response.getUpdatedAt(), response.getUpdatedBy(), response.getPort());
	    }
}
