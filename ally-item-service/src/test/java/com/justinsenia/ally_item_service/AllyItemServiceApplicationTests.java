package com.justinsenia.ally_item_service;

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

import com.justinsenia.ally_item_service.model.Item;
import com.justinsenia.ally_item_service.AllyItemServiceApplication;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = AllyItemServiceApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class AllyItemServiceApplicationTests {

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
    public void testGetAllItems() {
         HttpHeaders headers = new HttpHeaders();
         HttpEntity<String> entity = new HttpEntity<String>(null, headers);

         ResponseEntity<String> response = restTemplate.exchange(getRootUrl() + "/items",
         HttpMethod.GET, entity, String.class);
  
         Assertions.assertNotNull(response.getBody());
    }

    @Test
    public void testGetItemById() {
        Item item = restTemplate.getForObject(getRootUrl() + "/item/1", Item.class);
        System.out.println(item.getItemName());
        Assertions.assertNotNull(item);
    }

    @Test
    public void testCreateItem() {
        Item item = new Item();
        item.setItemName("testItemName");
        item.setItemDescription ("TestItemDescription");
        item.setCreatedBy("adminuser");
        item.setUpdatedBy("adminuser");
        item.setUpdatedAt(new Date());
        item.setCreatedAt(new Date());

        ResponseEntity<Item> postResponse = restTemplate.postForEntity(getRootUrl() + "/items", item, Item.class);
        Assertions.assertNotNull(postResponse);
        Assertions.assertNotNull(postResponse.getBody());
    }

    @Test
    public void testUpdatePost() {
         int id = 1;
         Item item = restTemplate.getForObject(getRootUrl() + "/items/" + id, Item.class);
         item.setItemName("testItemName");
         item.setItemDescription ("TestItemDescription");
         item.setUpdatedBy("adminuser");
         item.setUpdatedAt(new Date());

         restTemplate.put(getRootUrl() + "/items/" + id, item);

         Item updatedItem = restTemplate.getForObject(getRootUrl() + "/items/" + id, Item.class);
         Assertions.assertNotNull(updatedItem);
    }

    @Test
    public void testDeletePost() {
         int id = 2;
         Item item = restTemplate.getForObject(getRootUrl() + "/items/" + id, Item.class);
         Assertions.assertNotNull(item);

         restTemplate.delete(getRootUrl() + "/items/" + id);
    
         try {
              item = restTemplate.getForObject(getRootUrl() + "/items/" + id, Item.class);
         } catch (final HttpClientErrorException e) {
        	 Assertions.assertEquals(e.getStatusCode(), HttpStatus.NOT_FOUND);
     }
  }

}