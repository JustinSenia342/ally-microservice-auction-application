package com.justinsenia.ally_auction_service.controller;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.justinsenia.ally_auction_service.exception.BidIsTooLowException;
import com.justinsenia.ally_auction_service.exception.ImmediateOutbidException;
import com.justinsenia.ally_auction_service.exception.NewHighBidderException;
import com.justinsenia.ally_auction_service.exception.ReserveNotMetException;
import com.justinsenia.ally_auction_service.exception.ResourceNotFoundException;
import com.justinsenia.ally_auction_service.exception.UnhandledUseCaseException;
import com.justinsenia.ally_auction_service.model.Auction;
import com.justinsenia.ally_auction_service.model.Bid;
import com.justinsenia.ally_auction_service.service.AuctionService;

@RestController
//@RequestMapping(path="/api/v1")
public class AllyAuctionServiceController {
	

	@Autowired
    private AuctionService auctionService;
	
	
	
	/********************************************************/
	
	// POST /auctionItems
	@PostMapping("/auctionItems")
	public Map<String, String> postNewAuction(@Valid @RequestBody Auction auction) {	
			
			auction.setCurrentBid(BigDecimal.valueOf(0.00));
			auction.setBidderName("");
			auction.setMaxAutoBidAmount(BigDecimal.valueOf(0.00));
			auction.setReservePrice(auction.getReservePrice());
			auction.setItem(auction.getItem());

			auctionService.save(auction);
			
			Map<String, String> uriVariables = new HashMap<>();
			uriVariables.put("auctionItemId", Long.toString(auction.getAuctionItemId()));
			
			return uriVariables;
			
	  }


	// GET /auctionItems
	@GetMapping("/auctionItems")
    public List<Auction> getAllAuctions() {
		
		return auctionService.findAll();
		
    }
	

	// GET /auctionItems/{auctionItemId}
	/*@GetMapping("/auctionItems/{auction_item_id}")
    public ResponseEntity<Auction> getAuctionById(
    @PathVariable(value = "auction_item_id") Long auctionId) throws ResourceNotFoundException {
		Auction auction = auctionService.findById(auctionId)
        .orElseThrow(() -> new ResourceNotFoundException("Auction not found on :: "+ auctionId));
        
        return ResponseEntity.ok().body(auction);
    }*/
	
	// GET /auctionItems/{auctionItemId}
	@GetMapping("/auctionItems/{auction_item_id}")
    public Auction getAuctionById(
    @PathVariable(value = "auction_item_id") Long auctionId) throws ResourceNotFoundException {
		Auction auction = auctionService.findById(auctionId)
        .orElseThrow(() -> new ResourceNotFoundException("Auction not found on :: "+ auctionId));
		
        return auction;
    }

	
	// POST /bids *********************************************************
	@PostMapping("/bids")
	public ResponseEntity<Auction> updateAuctionById(@Valid @RequestBody Bid bid, HttpServletRequest request)
			throws ResourceNotFoundException, ImmediateOutbidException, ReserveNotMetException, 
			UnhandledUseCaseException, NewHighBidderException, BidIsTooLowException {	
  
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
			throw new BidIsTooLowException(
					"Bid amount is invalid: Newly submitted bid is less than the current bid");
		}
		
		boolean isReservePriceMet = ( newMaxAutoBidAmount.doubleValue() > reservePrice.doubleValue() ) ? true : false;
		
		boolean isNewMaxGreater = ( newMaxAutoBidAmount.doubleValue() > oldMaxAutoBidAmount.doubleValue() + 1.00) ? true : false;
		
		//check for null values too
		
		if ( isReservePriceMet == true && isNewMaxGreater == true ) {
			
			auction.setCurrentBid(oldMaxAutoBidAmount.add(BigDecimal.valueOf(1.00)));
			auction.setMaxAutoBidAmount(newMaxAutoBidAmount);
			auction.setBidderName(bid.getBidderName());
			
	        auctionService.save(auction);
	        throw new NewHighBidderException( oldBidderName + 
	        		" Has been outbid on auction item #" + auction.getAuctionItemId() + 
	        		" ( " + auction.getItem().getItemId() + " ) ");
		
		}
		else if ( isReservePriceMet == false && isNewMaxGreater == false ) {
			auction.setCurrentBid(newMaxAutoBidAmount);

	        auctionService.save(auction);
	        throw new ImmediateOutbidException( newBidderName + 
	        		" Was immediately outbid for auction item #" + 
	        		auction.getAuctionItemId() + " ( " + auction.getItem().getItemId() + 
	        		" ) upon bid submission");
	        
		}
		else if ( isReservePriceMet == true && isNewMaxGreater == false ) {
			auction.setCurrentBid(newMaxAutoBidAmount);

	        auctionService.save(auction);
	        throw new ImmediateOutbidException( newBidderName + 
	        		" Was immediately outbid for auction item #" + 
	        		auction.getAuctionItemId() + " ( " + auction.getItem().getItemId() + 
	        		" ) upon bid submission");
	        
		}
		if ( isReservePriceMet == false && isNewMaxGreater == true ) {
			auction.setCurrentBid(newMaxAutoBidAmount);
			auction.setMaxAutoBidAmount(newMaxAutoBidAmount);
			auction.setBidderName(bid.getBidderName());

	        auctionService.save(auction);

	        throw new ReserveNotMetException( newBidderName + 
	        		" Is the current high bidder for auction item #" + 
	        		auction.getAuctionItemId() + " ( " + auction.getItem().getItemId() + 
	        		" ), but hasn't met the reserve ");
	        
		}
		else {
			throw new UnhandledUseCaseException("Unhandled Use-Case");
			
		}
		
        
	}

	
	
}