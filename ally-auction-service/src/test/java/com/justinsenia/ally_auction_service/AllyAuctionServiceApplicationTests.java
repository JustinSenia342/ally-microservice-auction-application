package com.justinsenia.ally_auction_service;

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

import com.justinsenia.ally_auction_service.model.Auction;
import com.justinsenia.ally_auction_service.model.Item;
import com.justinsenia.ally_auction_service.AllyAuctionServiceApplication;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = AllyAuctionServiceApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class AllyAuctionServiceApplicationTests {

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
    public void testGetAllAuctions() {
         HttpHeaders headers = new HttpHeaders();
         HttpEntity<String> entity = new HttpEntity<String>(null, headers);

         ResponseEntity<String> response = restTemplate.exchange(getRootUrl() + "/auctions",
         HttpMethod.GET, entity, String.class);
  
         Assertions.assertNotNull(response.getBody());
    }

    @Test
    public void testGetAuctionById() {
        Auction auction = restTemplate.getForObject(getRootUrl() + "/auction/1", Auction.class);
        System.out.println(auction.getReservePrice());
        Assertions.assertNotNull(auction);
    }

    @Test
    public void testCreateAuction() {
        Auction auction = new Auction();
        Item item = new Item();
        
        item.setItemId("Test-itemId");
        item.setDescription("Test-itemDescription");
        item.setCreatedAt(new Date());
        item.setCreatedBy("Test-createdBy (Item)");
        item.setUpdatedAt(new Date());
        item.setUpdatedBy("Test-createdBy (Item)");
        
        auction.setCurrentBid(333.33);
		auction.setBidderName("Test-BidderName");
		auction.setReservePrice(777.77);
		auction.setItemId("Test-itemId");
		auction.setDescription("Test-description");
		//auction.setItem(item);
		auction.setCreatedAt(new Date());
		auction.setCreatedBy("Test-CreatedBy (Auction)");
		auction.setUpdatedAt(new Date());
		auction.setUpdatedBy("Test-UpdatedBy (Auction)");

        ResponseEntity<Auction> postResponse = restTemplate.postForEntity(getRootUrl() + "/auctions", auction, Auction.class);
        Assertions.assertNotNull(postResponse);
        Assertions.assertNotNull(postResponse.getBody());
    }

    @Test
    public void testUpdatePost() {
         int id = 1;
         Auction auction = restTemplate.getForObject(getRootUrl() + "/auctions/" + id, Auction.class);
         
         auction.setCurrentBid(888.88);
  		 auction.setBidderName("Test-bidderName");
  		 auction.setUpdatedAt(new Date());
  		 auction.setUpdatedBy("Test-updatedBy");

         restTemplate.put(getRootUrl() + "/auctions/" + id, auction);

         Auction updatedAuction = restTemplate.getForObject(getRootUrl() + "/auctions/" + id, Auction.class);
         Assertions.assertNotNull(updatedAuction);
    }

    @Test
    public void testDeletePost() {
         int id = 2;
         Auction auction = restTemplate.getForObject(getRootUrl() + "/auctions/" + id, Auction.class);
         Assertions.assertNotNull(auction);

         restTemplate.delete(getRootUrl() + "/auctions/" + id);
    
         try {
              auction = restTemplate.getForObject(getRootUrl() + "/auctions/" + id, Auction.class);
         } catch (final HttpClientErrorException e) {
        	 Assertions.assertEquals(e.getStatusCode(), HttpStatus.NOT_FOUND);
     }
  }

}