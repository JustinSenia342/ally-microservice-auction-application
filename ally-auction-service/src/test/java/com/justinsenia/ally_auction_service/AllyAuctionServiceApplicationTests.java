package com.justinsenia.ally_auction_service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
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
    	
    	// get /auctionItems
         HttpHeaders headers = new HttpHeaders();
         HttpEntity<String> entity = new HttpEntity<String>(null, headers);

         ResponseEntity<String> response = restTemplate.exchange(getRootUrl() + "/auctions",
         HttpMethod.GET, entity, String.class);
  
         Assertions.assertNotNull(response.getBody());
    }

    @Test
    public void testGetAuctionById() {
    	
    	// get /auctionItems/{auction_item_id}
        Auction auction = restTemplate.getForObject(getRootUrl() + "/auction/1", Auction.class);
        Assertions.assertNotNull(auction);
    }

    @Test
    public void testCreateAuction() {
    	
    	//post /auctionItems
    	
        Auction auction = new Auction();
        Item item = new Item();
        
        item.setItemId("Test-itemId");
        item.setDescription("Test-description");
        
        auction.setReservePrice(BigDecimal.valueOf(777.77));
        auction.setCurrentBid(BigDecimal.valueOf(0.00));
		auction.setBidderName("");
		auction.setMaxAutoBidAmount(BigDecimal.valueOf(0.00));
		auction.setReservePrice(auction.getReservePrice());
		auction.setItem(item);

        ResponseEntity<Auction> postResponse = restTemplate.postForEntity(getRootUrl() + "/auctions", auction, Auction.class);
        Assertions.assertNotNull(postResponse);
        Assertions.assertNotNull(postResponse.getBody());
        
    }

    @Test
    public void testUpdatePost() {
    	
    	// post /bids
    	
    	
    	
    	long auctionItemId = 1;
    	
    	Auction auction = restTemplate.getForObject(getRootUrl() + "/auctions/" + auctionItemId, Auction.class);
        
    	String bidderName = "Test-bidderName-999";
    	BigDecimal maxAutoBidAmount = BigDecimal.valueOf(999.99);
         
        auction.setBidderName("Test-bidderName-999");
        auction.setMaxAutoBidAmount(BigDecimal.valueOf(999.99));

        Assertions.assertThrows(NumberFormatException.class, () -> {
        	 Integer.parseInt("One");
        });
          
        restTemplate.put(getRootUrl() + "/auctions/" + auctionItemId, auction);

        Auction updatedAuction = restTemplate.getForObject(getRootUrl() + "/auctions/" + auctionItemId, Auction.class);
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