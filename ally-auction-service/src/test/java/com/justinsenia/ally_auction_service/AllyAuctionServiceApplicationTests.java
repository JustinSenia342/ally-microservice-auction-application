package com.justinsenia.ally_auction_service;

import java.math.BigDecimal;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;

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

    
    // get /auctionItems
    @Test
    public void getAllAuctions_AuctionsExistInDatabase_ReturnsListOfAuctions() {
    	
         HttpHeaders headers = new HttpHeaders();
         HttpEntity<String> entity = new HttpEntity<String>(null, headers);

         ResponseEntity<String> response = restTemplate.exchange(getRootUrl() + "/auctions",
         HttpMethod.GET, entity, String.class);
  
         Assertions.assertNotNull(response.getBody());
    }

    
    // get /auctionItems/{auction_item_id}
    @Test
    public void getAuctionById_WithValidArgument_ReturnsSpecifiedAuction() {
    	
        Auction auction = restTemplate.getForObject(getRootUrl() + "/auction/1", Auction.class);
        Assertions.assertNotNull(auction);
    }

    
    //post /auctionItems
    @Test
    public void postNewAuction_WithValidRequest_CreatesNewAuction() {
    	
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
        //Assertions.assertNotNull(postResponse);
        Assertions.assertNotNull(postResponse.getBody());
        
    }

    
    // post /bids
    @Test
    public void updateAuctionById_WithValidRequest_UpdatesAuctionWithNewBid() throws Exception {
    	
    	// Tests Not implemented yet
    	
    }


}