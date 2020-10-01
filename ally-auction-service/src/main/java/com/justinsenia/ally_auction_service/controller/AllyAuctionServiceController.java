package com.justinsenia.ally_auction_service.controller;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

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
import com.justinsenia.ally_auction_service.model.Bid;
import com.justinsenia.ally_auction_service.model.Item;
import com.justinsenia.ally_auction_service.repository.AuctionRepository;
import com.justinsenia.ally_auction_service.service.AuctionService;
import com.google.gson.Gson;
import com.justinsenia.ally_auction_service.exception.ResourceNotFoundException;

@RestController
@RequestMapping(path="/api/v1")
public class AllyAuctionServiceController {
	
	//@Autowired
	//private AuctionRepository auctionRepository;
	
	@Autowired
    private AuctionService auctionService;
	
	
	//@GetMapping("/auctions")
    //public List<Auction> getAllAuctions() {
	//	return auctionRepository.findAll();
    //}
	
	
	// Returns JSON object of all auction items
	//@GetMapping("/auctions")
    //public String getAllAuctions() {
	//	List<Auction> auctions = auctionRepository.findAll();
	//	
	//	String auctionsListJson = new Gson().toJson(auctions);
	//	
	//	return auctionsListJson;
    //}
	
	// Returns JSON of a single specified auction item based on auction_item_id value
	//@GetMapping("/auctions/{auction_item_id}")
    //public ResponseEntity<Auction> getAuctionById(
    //@PathVariable(value = "auction_item_id") Long auctionId) throws ResourceNotFoundException {
    //    Auction auction = auctionRepository.findById(auctionId)
    //    .orElseThrow(() -> new ResourceNotFoundException("Auction not found on :: "+ auctionId));
    //    
    //    return ResponseEntity.ok().body(auction);
    //}
	
	
	//    public Auction createAuction(@Valid @RequestBody Auction auction) {
	//@PostMapping("/auctions")
    //public Auction createAuction(@Valid @RequestBody Auction auction, HttpServletRequest request) {
	/*@PostMapping("/auctions")
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
    }*/
	
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
	
/*	@PostMapping("/auction")
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
    }*/
	
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
	
	
	
	
	
	
	
	/********************************************************/
	
	// POST /auctionItems
	//  public Auction createAuction(@Valid @RequestBody Auction auction) {
		//@PostMapping("/auctions")
	  //public Auction createAuction(@Valid @RequestBody Auction auction, HttpServletRequest request) {
	@PostMapping("/auctionItems")
	public Map<String, String> postNewAuction(@Valid @RequestBody Auction auction, HttpServletRequest request) {	
			
			//Item item = auction.getItem();
			//Item item = new Gson().fromJson(auction.getItem(), Item.class);
			
			auction.setCurrentBid(BigDecimal.valueOf(0.00));
			auction.setBidderName(null);
			auction.setMaxAutoBidAmount(BigDecimal.valueOf(0.00));
			auction.setReservePrice(auction.getReservePrice());
			//auction.setItemId(auction.getItemId());
			//auction.setDescription(auction.getDescription());
			auction.setItem(auction.getItem());
			//auction.setCreatedAt(new Date());
			//auction.setCreatedBy(request.getRemoteUser());
			//auction.setUpdatedAt(new Date());
			//auction.setUpdatedBy(request.getRemoteUser());
			
			// Original****
	      // return auctionRepository.save(auction);
	      
	      // List<Auction> auctions = auctionRepository.findAll();
			
			// String auctionsListJson = new Gson().toJson(auctions);
			
			// return auctionsListJson;
	      
				//auctionRepository.save(auction);
			auctionService.save(auction);
			//String createdAuctionJson = new Gson().toJson(auction);
			
			Map<String, String> uriVariables = new HashMap<>();
			uriVariables.put("auctionItemId", Long.toString(auction.getAuctionItemId()));
			
			return uriVariables;
			
	      //return Long.toString(auction.getAuctionItemId());
	  }
	/*
	 Example Request Body:
		{
			“reservePrice”: 10450.00,
			“item”: {
				“itemId”: “abcd”,
				“description”: “item description”
			}
		}
		
		Example Response:
		{
			"auctionItemId": "1234"
		}
 
	 */
	
	
	
	
	
	
	//String auctionsListJson = new Gson().toJson(auctions);
	
	// GET /auctionItems
	/*@GetMapping("/auctionItems")
    public List<Auction> getAllAuctions() {
		//return auctionRepository.findAll();
		return auctionService.findAll();
    }*/
	
	@GetMapping("/auctionItems")
    public List<Auction> getAllAuctions() {
		//return auctionRepository.findAll();
		
		return auctionService.findAll();
		
		//String auctionsListJson = new Gson().toJson(auctionService.findAll());
		
		//return auctionsListJson;
    }
	
	/*
	 Example Response:
		{
			[
		{
			“auctionItemId”: “1234”,
			“currentBid”: 0.00,
			“reservePrice”: 10450.00,
			“item”: {
				“itemId”: “abcd”,
				“description”: “item description”
			}
		},
		{
			“auctionItemId”: “1235”,
			“currentBid”: 2950.00,
			“bidderName”: “ABC Dealership”
		“reservePrice”: 2499.00,
			“item”: {
				“itemId”: “efgh”,
				“description”: “another item description”
			}
		}
			]
		}
	 */
	
	
	
	
	// GET /auctionItems/{auctionItemId}
	@GetMapping("/auctionItems/{auction_item_id}")
    public ResponseEntity<Auction> getAuctionById(
    @PathVariable(value = "auction_item_id") Long auctionId) throws ResourceNotFoundException {
        //Auction auction = auctionRepository.findById(auctionId)
		Auction auction = auctionService.findById(auctionId)
        .orElseThrow(() -> new ResourceNotFoundException("Auction not found on :: "+ auctionId));
        
        return ResponseEntity.ok().body(auction);
    }
	/*
	 Example Response:
		{
			“auctionItemId”: “1234”,
			“currentBid”: 0.00,
			“reservePrice”: 10450.00,
			“item”: {
				“itemId”: “abcd”,
				“description”: “item description”
			}
		}
 
	 */
	
	
	
	
	// POST /bids *********************************************************
	//public ResponseEntity<Auction> updateAuction(
	@PostMapping("/bids")
	public ResponseEntity<Auction> updateAuctionById(@Valid @RequestBody Bid bid, HttpServletRequest request) throws ResourceNotFoundException {	
    //public ResponseEntity<Auction> updateAuctionById(
    //@PathVariable(value = "auction_item_id") Long auctionItemId) throws ResourceNotFoundException {
    //    Auction auction = auctionRepository.findById(auctionItemId)
    //      .orElseThrow(() -> new ResourceNotFoundException("Auction not found on :: "+ auctionItemId));
  
		Long auctionItemId = Long.valueOf(bid.getAuctionItemId());
		Auction auction = auctionService.findById(auctionItemId)
				.orElseThrow(() -> new ResourceNotFoundException("Auction not found on :: "+ auctionItemId));
		        
		BigDecimal currentBid = auction.getCurrentBid();
		BigDecimal reservePrice = auction.getReservePrice();
		BigDecimal oldMaxAutoBidAmount = auction.getMaxAutoBidAmount();
		BigDecimal newMaxAutoBidAmount = bid.getMaxAutoBidAmount();
		String oldBidderName = auction.getBidderName();
		String newBidderName = bid.getBidderName();
		
		if (newMaxAutoBidAmount.doubleValue() <= currentBid.doubleValue()) {
			//Throw (() -> new BidIsTooLowException("Newly submitted bid is less than the current bid"));
			final Auction updatedAuction = auction;
	        return ResponseEntity.ok(updatedAuction);
		}
		
		boolean isReservePriceMet = ( newMaxAutoBidAmount.doubleValue() > reservePrice.doubleValue() ) ? true : false;
		
		boolean isNewMaxGreater = ( newMaxAutoBidAmount.doubleValue() > oldMaxAutoBidAmount.doubleValue() + 1.00) ? true : false;
		
		//check for null values too
		
		if ( isReservePriceMet == true && isNewMaxGreater == true ) {
			
			auction.setCurrentBid(oldMaxAutoBidAmount.add(BigDecimal.valueOf(1.00)));
			auction.setMaxAutoBidAmount(newMaxAutoBidAmount);
			auction.setBidderName(bid.getBidderName());
			//auction.setUpdatedAt(new Date());
	 		//auction.setUpdatedBy(auction.getBidderName());
	        auctionService.save(auction);
	        //Throw (() -> new NewHighBidderException( oldBidderName + " Has been outbid on auction item #" + auction.getAuctionItemId() + " ( " + auction.getItemId() + " ) "));
	        final Auction updatedAuction = auction;
	        return ResponseEntity.ok(updatedAuction);
		}
		else if ( isReservePriceMet == false && isNewMaxGreater == false ) {
			auction.setCurrentBid(newMaxAutoBidAmount);
			//auction.setUpdatedAt(new Date());
	 		//auction.setUpdatedBy(auction.getBidderName());
	        auctionService.save(auction);
	        //Throw (() -> new ImmediateOutbidException( newBidderName + " Was immediately outbid for auction item #" + auction.getAuctionItemId() + " ( " + auction.getItemId() + " ) upon bid submission"));
	        final Auction updatedAuction = auction;
	        return ResponseEntity.ok(updatedAuction);
		}
		else if ( isReservePriceMet == true && isNewMaxGreater == false ) {
			auction.setCurrentBid(newMaxAutoBidAmount);
			//auction.setUpdatedAt(new Date());
	 		//auction.setUpdatedBy(auction.getBidderName());
	        auctionService.save(auction);
	        //Throw (() -> new ImmediateOutbidException( newBidderName + " Was immediately outbid for auction item #" + auction.getAuctionItemId() + " ( " + auction.getItemId() + " ) upon bid submission"));
	        final Auction updatedAuction = auction;
	        return ResponseEntity.ok(updatedAuction);
		}
		if ( isReservePriceMet == false && isNewMaxGreater == true ) {
			auction.setCurrentBid(newMaxAutoBidAmount);
			auction.setMaxAutoBidAmount(newMaxAutoBidAmount);
			auction.setBidderName(bid.getBidderName());
			//auction.setUpdatedAt(new Date());
	 		//auction.setUpdatedBy(auction.getBidderName());
	        auctionService.save(auction);
	        //throw new NotMetReserveException();
	        //Throw (() -> new NotMetReserveException( newBidderName + " Is the current high bidder for auction item #" + auction.getAuctionItemId() + " ( " + auction.getItemId() + " ), but hasn't met the reserve "));
	        final Auction updatedAuction = auction;
	        return ResponseEntity.ok(updatedAuction);
		}
		else {
			//Throw (() -> new UnhandledCaseException("Unhandled Use-Case"));
			final Auction updatedAuction = auction;
	        return ResponseEntity.ok(updatedAuction);
		}
		
		
 		//auction.setUpdatedAt(new Date());
 		//auction.setUpdatedBy(auction.getBidderName());
        //auctionService.save(auction);
        
        //return ResponseEntity.ok(updatedAuction);
        //final Auction updatedAuction = auction;
        //return ResponseEntity.ok(updatedAuction);
        
	}
	/*
	 Ex:
	 Example Request Body:
		{
			“auctionItemId”: “1234”,
			“maxAutoBidAmount”: 9500.00,
			“bidderName”: “ABC Dealership”
		}
	 */
	
	
}