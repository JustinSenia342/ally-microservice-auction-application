package com.justinsenia.ally_auction_service.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.justinsenia.ally_auction_service.model.Auction;
import com.justinsenia.ally_auction_service.repository.AuctionRepository;
import com.google.gson.Gson;
import com.justinsenia.ally_auction_service.exception.ResourceNotFoundException;

@RestController
@RequestMapping(path="/api/v1")
public class AllyAuctionServiceController {
	
	@Autowired
	private AuctionRepository auctionRepository;
	
	//@GetMapping("/auctions")
    //public List<Auction> getAllAuctions() {
	//	return auctionRepository.findAll();
    //}
	
	
	// Returns JSON object of all auction items
	@GetMapping("/auctions")
    public String getAllAuctions() {
		List<Auction> auctions = auctionRepository.findAll();
		
		String auctionsListJson = new Gson().toJson(auctions);
		
		return auctionsListJson;
    }
	
	// Returns JSON of a single specified auction item based on auction_item_id value
	@GetMapping("/auctions/{auction_item_id}")
    public ResponseEntity<Auction> getAuctionById(
    @PathVariable(value = "auction_item_id") Long auctionId) throws ResourceNotFoundException {
        Auction auction = auctionRepository.findById(auctionId)
        .orElseThrow(() -> new ResourceNotFoundException("Auction not found on :: "+ auctionId));
        
        return ResponseEntity.ok().body(auction);
    }
	
	
	//    public Auction createAuction(@Valid @RequestBody Auction auction) {
	//@PostMapping("/auctions")
    //public Auction createAuction(@Valid @RequestBody Auction auction, HttpServletRequest request) {
	@PostMapping("/auctions")
    public String createAuction(@Valid @RequestBody Auction auction, HttpServletRequest request) {	
		
		
		auction.setCurrentBid(0.00);
		auction.setBidderName(null);
		auction.setMaxAutoBidAmount(0.00);
		auction.setReservePrice(auction.getReservePrice());
		auction.setItemId(auction.getItemId());
		auction.setDescription(auction.getDescription());
		//auction.setItem(auction.getItem());
		auction.setCreatedAt(new Date());
		auction.setCreatedBy(request.getRemoteUser());
		auction.setUpdatedAt(new Date());
		auction.setUpdatedBy(request.getRemoteUser());
		
		// Original****
        // return auctionRepository.save(auction);
        
        // List<Auction> auctions = auctionRepository.findAll();
		
		// String auctionsListJson = new Gson().toJson(auctions);
		
		// return auctionsListJson;
        
		auctionRepository.save(auction);
		
		String createdAuctionJson = new Gson().toJson(auction);
		
        return createdAuctionJson;
    }
	
	//@PutMapping("/auction/{id}")
    //public ResponseEntity<Auction> updateAuction(
	/*@PutMapping("/auction/{id}")
    public String updateAuction(
    @PathVariable(value = "id") Long auctionId,
    @Valid @RequestBody Auction auctionDetails, HttpServletRequest request) throws ResourceNotFoundException {
         Auction auction = auctionRepository.findById(auctionId)
          .orElseThrow(() -> new ResourceNotFoundException("Auction not found on :: "+ auctionId));
  
        auction.setCurrentBid(auction.getCurrentBid());
 		auction.setBidderName(auction.getBidderName());
 		auction.setUpdatedAt(new Date());
 		auction.setUpdatedBy(request.getRemoteUser());
        final Auction updatedAuction = auctionRepository.save(auction);
        //return ResponseEntity.ok(updatedAuction);
        
        String updatedAuctionJson = new Gson().toJson(ResponseEntity.ok(updatedAuction));
		
        return updatedAuctionJson;
    }*/
	
	//@PutMapping("/auction/{id}")
    //public ResponseEntity<Auction> updateAuction(
	/*@PostMapping("/auction/{auction_item_id}")
    public String updateAuctionById(
    @PathVariable(value = "auction_item_id") Long auctionItemId) throws ResourceNotFoundException {
        Auction auction = auctionRepository.findById(auctionItemId)
          .orElseThrow(() -> new ResourceNotFoundException("Auction not found on :: "+ auctionItemId));
  
        auction.setCurrentBid(auction.getCurrentBid());
        auction.setMaxAutoBidAmount(auction.getMaxAutoBidAmount());
 		auction.setBidderName(auction.getBidderName());
 		auction.setUpdatedAt(new Date());
 		auction.setUpdatedBy(auction.getBidderName());
        final Auction updatedAuction = auctionRepository.save(auction);
        //return ResponseEntity.ok(updatedAuction);
        
        String updatedAuctionJson = new Gson().toJson(ResponseEntity.ok(updatedAuction));
		
        return updatedAuctionJson;
    }*/
	
	@PostMapping("/auction")
    public String updateAuctionById(Long auctionItemId) throws ResourceNotFoundException {
		
		Auction auction = auctionRepository.findById(auctionItemId)
		          .orElseThrow(() -> new ResourceNotFoundException("Auction not found on :: "+ auctionItemId));
  
        auction.setCurrentBid(auction.getCurrentBid());
        auction.setMaxAutoBidAmount(auction.getMaxAutoBidAmount());
 		auction.setBidderName(auction.getBidderName());
 		auction.setUpdatedAt(new Date());
 		auction.setUpdatedBy(auction.getBidderName());
        final Auction updatedAuction = auctionRepository.save(auction);
        //return ResponseEntity.ok(updatedAuction);
        
        String updatedAuctionJson = new Gson().toJson(ResponseEntity.ok(updatedAuction));
		
        return updatedAuctionJson;
    }
	
	/*
	@DeleteMapping("/auction/{id}")
	public Map<String, Boolean> deleteAuction(
	@PathVariable(value = "id") Long auctionId) throws Exception {
       Auction auction = auctionRepository.findById(auctionId)
          .orElseThrow(() -> new ResourceNotFoundException("Auction not found on :: "+ auctionId));

       auctionRepository.delete(auction);
       Map<String, Boolean> response = new HashMap<>();
       response.put("deleted", Boolean.TRUE);
       return response;
	}
	*/
}