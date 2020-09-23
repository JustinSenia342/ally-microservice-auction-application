package com.justinsenia.ally_user_service.tests;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.mockito.Mockito.when;

import java.util.Date;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.HttpClientErrorException;

import com.justinsenia.ally_user_service.model.User;
import com.justinsenia.ally_user_service.AllyUserServiceApplication;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = AllyUserServiceApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class AllyUserServiceApplicationTests {

    @Autowired
    private TestRestTemplate restTemplate;

    @LocalServerPort
    private int port;

    private String getRootUrl() {
        return "http://localhost:" + port;
    }

    @Test
    public void contextLoads() {

    }

    @Test
    public void testGetAllUsers() {
         HttpHeaders headers = new HttpHeaders();
         HttpEntity<String> entity = new HttpEntity<String>(null, headers);

         ResponseEntity<String> response = restTemplate.exchange(getRootUrl() + "/users",
         HttpMethod.GET, entity, String.class);
  
         Assertions.assertNotNull(response.getBody());
    }

    @Test
    public void testGetUserById() {
        User user = restTemplate.getForObject(getRootUrl() + "/users/1", User.class);
        System.out.println(user.getUsername());
        Assertions.assertNotNull(user);
    }

    @Test
    public void testCreateUser() {
        User user = new User();
        user.setUsername("adminuser");
        user.setPassword ("adminpass");
        user.setEmailAddress("admin@gmail.com");
        user.setCreatedBy("adminuser");
        user.setUpdatedBy("adminuser");
        user.setUpdatedAt(new Date());
        user.setCreatedAt(new Date());

        ResponseEntity<User> postResponse = restTemplate.postForEntity(getRootUrl() + "/users", user, User.class);
        Assertions.assertNotNull(postResponse);
        Assertions.assertNotNull(postResponse.getBody());
    }

    @Test
    public void testUpdatePost() {
         int id = 1;
         User user = restTemplate.getForObject(getRootUrl() + "/users/" + id, User.class);
         user.setUsername("adminuser");
         user.setPassword("adminpass");
         user.setEmailAddress("admin@gmail.com");
         user.setCreatedBy("adminuser");
         user.setUpdatedBy("adminuser");

         restTemplate.put(getRootUrl() + "/users/" + id, user);

         User updatedUser = restTemplate.getForObject(getRootUrl() + "/users/" + id, User.class);
         Assertions.assertNotNull(updatedUser);
    }

    @Test
    public void testDeletePost() {
         int id = 2;
         User user = restTemplate.getForObject(getRootUrl() + "/users/" + id, User.class);
         Assertions.assertNotNull(user);

         restTemplate.delete(getRootUrl() + "/users/" + id);
    
         try {
              user = restTemplate.getForObject(getRootUrl() + "/users/" + id, User.class);
         } catch (final HttpClientErrorException e) {
        	 Assertions.assertEquals(e.getStatusCode(), HttpStatus.NOT_FOUND);
     }
  }

}