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
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.justinsenia.ally_auction_service.exception.BidIsTooLowException;
import com.justinsenia.ally_auction_service.exception.ImmediateOutbidException;
import com.justinsenia.ally_auction_service.exception.NewHighBidderByDefaultException;
import com.justinsenia.ally_auction_service.exception.NewHighBidderException;
import com.justinsenia.ally_auction_service.exception.ReserveNotMetException;
import com.justinsenia.ally_auction_service.exception.ResourceNotFoundException;
import com.justinsenia.ally_auction_service.exception.UnhandledUseCaseException;
import com.justinsenia.ally_auction_service.model.Auction;
import com.justinsenia.ally_auction_service.model.Bid;
import com.justinsenia.ally_auction_service.service.AuctionService;

@RestController @CrossOrigin(origins = "http://localhost:4200")
//@RequestMapping(path="/api/v1")
public class AllyAuctionServiceController {
	

	@Autowired
    private AuctionService auctionService;
	
	
	// POST /auctionItems ***********************************************
	// Creates a new auction, returns the newly created auction Id in Json Format
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


	// GET /auctionItems *************************************************
	// Returns list of all auctions
	@GetMapping("/auctionItems")
    public List<Auction> getAllAuctions() {
		
		return auctionService.findAll();
		
    }
	
	
	// GET /auctionItems/{auctionItemId} **********************************
	// Returns a single auction 
	@GetMapping("/auctionItems/{auction_item_id}")
    public Auction getAuctionById(
    @PathVariable(value = "auction_item_id") Long auctionItemId) throws ResourceNotFoundException {
		Auction auction = auctionService.findById(auctionItemId)
        .orElseThrow(() -> new ResourceNotFoundException("Auction not found on :: "+ auctionItemId));
		
        return auction;
    }
	
	// Post /auctionItems/id **********************************
	// Returns a single auction 
	//@PostMapping("/auctionItems/id")
    //public Auction getAuctionByIdPost(
	//public Auction getAuctionByIdPost(@Valid @RequestBody Long auctionItemId)
	//		throws ResourceNotFoundException {
	//	Auction auction = auctionService.findById(auctionItemId)
	//	        .orElseThrow(() -> new ResourceNotFoundException("Auction not found on :: "+ auctionItemId));
				
	//	        return auction;
	//}

	
	// POST /bids *********************************************************
	// Updates persisted database values with new bid information
	// Additional logic is implemented to handle updating use-cases
	@PostMapping("/bids")
	public void updateAuctionById(@Valid @RequestBody Bid bid)
			throws ResourceNotFoundException, ImmediateOutbidException, ReserveNotMetException, 
			UnhandledUseCaseException, NewHighBidderException, BidIsTooLowException, NewHighBidderByDefaultException {	
  
		Long auctionItemId = Long.valueOf(bid.getAuctionItemId());
		Auction auction = auctionService.findById(auctionItemId)
				.orElseThrow(() -> new ResourceNotFoundException("Auction not found on :: "+ auctionItemId));
		
		
//		Test Return statement
		//Map<String, String> uriVariables = new HashMap<>();
		//uriVariables.put("auctionItemId", Long.toString(auction.getAuctionItemId()));	
		
		        
		BigDecimal currentBid = auction.getCurrentBid();
		BigDecimal reservePrice = auction.getReservePrice();
		BigDecimal oldMaxAutoBidAmount = auction.getMaxAutoBidAmount();
		BigDecimal newMaxAutoBidAmount = bid.getMaxAutoBidAmount();
		String oldBidderName = auction.getBidderName();
		String newBidderName = bid.getBidderName();
		
		if (newMaxAutoBidAmount.doubleValue() <= currentBid.doubleValue()) {
			throw new BidIsTooLowException(
					"Submitted bid amount is invalid: Newly submitted bid is less than the current bid");
		}
		
		boolean isNewAuction = ( currentBid.doubleValue() == 0.00 ) ? true : false;
		
		if (isNewAuction == true) {
			auction.setCurrentBid(oldMaxAutoBidAmount.add(BigDecimal.valueOf(1.00)));
			auction.setMaxAutoBidAmount(newMaxAutoBidAmount);
			auction.setBidderName(newBidderName);
			
			auctionService.save(auction);
	        throw new NewHighBidderByDefaultException( newBidderName +
	        		" is the new high bidder for item #" + auction.getAuctionItemId() + 
	        		" ( " + auction.getItem().getItemId() + " ) ");
		}
		
		boolean isReservePriceMet = ( newMaxAutoBidAmount.doubleValue() > reservePrice.doubleValue() ) ? true : false;
		
		boolean isNewMaxGreater = ( newMaxAutoBidAmount.doubleValue() > oldMaxAutoBidAmount.doubleValue() + 1.00) ? true : false;
		
		
		if ( isReservePriceMet == true && isNewMaxGreater == true ) {
			
			auction.setCurrentBid(oldMaxAutoBidAmount.add(BigDecimal.valueOf(1.00)));
			auction.setMaxAutoBidAmount(newMaxAutoBidAmount);
			auction.setBidderName(newBidderName);
			
	        auctionService.save(auction);
	        throw new NewHighBidderException( newBidderName +
	        		" is the new high bidder! " + oldBidderName + 
	        		" has been outbid on auction item #" + auction.getAuctionItemId() + 
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
			auction.setBidderName(newBidderName);

	        auctionService.save(auction);

	        throw new ReserveNotMetException( newBidderName + 
	        		" Is the current high bidder for auction item #" + 
	        		auction.getAuctionItemId() + " ( " + auction.getItem().getItemId() + 
	        		" ), but the reserve price hasn't been met yet.");
	        
		}
		else {
			throw new UnhandledUseCaseException("Unhandled Use-Case");
			
		}
		
	}
	
	@DeleteMapping("/auctionItems/{auction_item_id}")
    public Map<String, Boolean> deleteAuctionById(@PathVariable(value = "auction_item_id") Long auctionItemId)
         throws ResourceNotFoundException {
        Auction auction = auctionService.findById(auctionItemId)
       .orElseThrow(() -> new ResourceNotFoundException("Auction not found for this id :: " + auctionItemId));

        auctionService.delete(auctionItemId);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
    }

}