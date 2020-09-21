package com.justinsenia.ally_user_service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(path="/user")
public class AllyUserServiceController {
	@Autowired
	
	private UserDaoRepository userDaoRepository;
	
	@PostMapping(path="/add") // Map ONLY POST Requests
	public @ResponseBody String addNewUser (@RequestParam String userName
			, @RequestParam String userPass) {
		
		UserDao n = new UserDao();
		n.setUserName(userName);
		n.setUserPass(userPass);
		userDaoRepository.save(n);
		return "Saved";
	}
	
	@GetMapping(path="/all")
	public @ResponseBody Iterable<UserDao> getAllUsers() {
		// This returns a JSON or XML with the users
		return userDaoRepository.findAll();
	} 
}