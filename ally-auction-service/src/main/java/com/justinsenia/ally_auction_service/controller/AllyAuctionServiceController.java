package com.justinsenia.ally_auction_service.controller;

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

import com.justinsenia.ally_auction_service.model.Auction;
import com.justinsenia.ally_auction_service.repository.AuctionRepository;
import com.justinsenia.ally_auction_service.exception.ResourceNotFoundException;

@RestController
@RequestMapping(path="/api/v1")
public class AllyAuctionServiceController {
	
	@Autowired
	private Environment environment;
	
	@Autowired
	private AuctionRepository auctionRepository;
	
	@GetMapping("/auctions")
    public List<Auction> getAllAuctions() {
        return auctionRepository.findAll();
    }
	
	@GetMapping("/auctions/{id}")
    public ResponseEntity<Auction> getAuctionById(
    @PathVariable(value = "id") Long auctionId) throws ResourceNotFoundException {
        Auction auction = auctionRepository.findById(auctionId)
        .orElseThrow(() -> new ResourceNotFoundException("Auction not found on :: "+ auctionId));
        return ResponseEntity.ok().body(auction);
    }
	
	@PostMapping("/auctions")
    public Auction createAuction(@Valid @RequestBody Auction auction) {
		
		auction.setCreatedBy(auction.getSellerName());
		auction.setUpdatedBy(auction.getSellerName());
        return auctionRepository.save(auction);
    }
	
	@PutMapping("/auction/{id}")
    public ResponseEntity<Auction> updateAuction(
    @PathVariable(value = "id") Long auctionId,
    @Valid @RequestBody Auction auctionDetails) throws ResourceNotFoundException {
         Auction auction = auctionRepository.findById(auctionId)
          .orElseThrow(() -> new ResourceNotFoundException("Auction not found on :: "+ auctionId));
  
        auction.setAuctionTitle(auctionDetails.getAuctionTitle());
        auction.setAuctionDescription(auctionDetails.getAuctionDescription());
        auction.setUpdatedAt(new Date());
        final Auction updatedAuction = auctionRepository.save(auction);
        return ResponseEntity.ok(updatedAuction);
    }
	
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
	
}