package com.justinsenia.ally_user_service.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.justinsenia.ally_user_service.model.User;
import com.justinsenia.ally_user_service.repository.UserRepository;
import com.justinsenia.ally_user_service.exception.ResourceNotFoundException;

@RestController
@RequestMapping(path="/api/v1")
public class AllyUserServiceController {
	
	@Autowired
	private Environment environment;
	
	@Autowired
	private UserRepository userRepository;
	
	@GetMapping("/users")
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }
	
	@GetMapping("/users/{id}")
    public ResponseEntity<User> getUserById(
    @PathVariable(value = "id") Long userId) throws ResourceNotFoundException {
        User user = userRepository.findById(userId)
        .orElseThrow(() -> new ResourceNotFoundException("User not found on :: "+ userId));
        return ResponseEntity.ok().body(user);
    }
	
	@PostMapping("/users")
    public User createUser(@Valid @RequestBody User user) {
        return userRepository.save(user);
    }
	
	@PutMapping("/users/{id}")
    public ResponseEntity<User> updateUser(
    @PathVariable(value = "id") Long userId,
    @Valid @RequestBody User userDetails) throws ResourceNotFoundException {
         User user = userRepository.findById(userId)
          .orElseThrow(() -> new ResourceNotFoundException("User not found on :: "+ userId));
  
        user.setUsername(userDetails.getUsername());
        user.setPassword(userDetails.getPassword());
        user.setEmailAddress(userDetails.getEmailAddress());
        user.setUpdatedAt(new Date());
        final User updatedUser = userRepository.save(user);
        return ResponseEntity.ok(updatedUser);
    }
	
	@DeleteMapping("/user/{id}")
	   public Map<String, Boolean> deleteUser(
	       @PathVariable(value = "id") Long userId) throws Exception {
	       User user = userRepository.findById(userId)
	          .orElseThrow(() -> new ResourceNotFoundException("User not found on :: "+ userId));

	       userRepository.delete(user);
	       Map<String, Boolean> response = new HashMap<>();
	       response.put("deleted", Boolean.TRUE);
	       return response;
	   }
	
	/*
	@GetMapping("/users")
	public List<UserDao> getAllUsers() {
		return userRepository.findAll();
	}
	
	@GetMapping("/user/{id}")
	public ResponseEntity<UserDao> getUserById(
	@PathVariable(value = "user_id") Long userId) {
		UserDao userDao = UserRepository.findById(userId);
		return ResponseEntity.ok().body(userDao);
	}
	
	*/
	/*
	@PostMapping(path="/add") // Map ONLY POST Requests
	public @ResponseBody String addNewUser (@RequestParam String userName
			, @RequestParam String userPass) {
		
		UserDao n = new UserDao();
		n.setUserName(userName);
		n.setUserPass(userPass);
		userRepository.save(n);
		return "Saved";
	}
	
	@GetMapping(path="/checkport/{userName}")
	public @ResponseBody UserDao retrieveUserData(@PathVariable String userName) {
		
		UserDao userDao = userRepository.findById(1);
		m.setPort(Integer.parseInt(environment.getProperty("local.server.port")));
		
		return m;
	}
	*/
	
	
	/*
	 	@GetMapping(path="/checkport/{userName}")
	public UserDao retrieveUserDao
	(@PathVariable String userName){
		
		//UserDao userDao = userRepository.findByUserName(userName);
		
		//userDao.setPort(Integer.parseInt(environment.getProperty("local.server.port")));
		
		return userDao;
	} 
	 
	 */
	
	/*
	@GetMapping(path="/all")
	public @ResponseBody Iterable<UserDao> getAllUsers() {
		
		// This returns a JSON or XML with the users
		return userRepository.findAll();
	} 
	*/
}