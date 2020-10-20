package com.justinsenia.ally_auction_service.controller;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
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

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController @CrossOrigin(origins = "http://localhost:4200")
//@RequestMapping(path="/api/v1")
public class AllyAuctionServiceController {
	
	private final AuctionService auctionService;
	
	
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

	
	// POST /bids *********************************************************
	// Updates persisted database values with new bid information
	// Additional logic is implemented to handle updating use-cases
	@PostMapping("/bids")
	public void updateAuctionById(@Valid @RequestBody Bid bid)
			throws ResourceNotFoundException, ImmediateOutbidException, 
			ReserveNotMetException, UnhandledUseCaseException,
			NewHighBidderException, BidIsTooLowException, 
			NewHighBidderByDefaultException {	
  
		Long auctionItemId = Long.valueOf(bid.getAuctionItemId());
		Auction auction = auctionService.findById(auctionItemId)
				.orElseThrow(() -> new ResourceNotFoundException("Auction not found on :: "+ auctionItemId));
		
		
		Map<String, String> bidResults = auctionService.makeBid(auction, bid);
		
		
		switch(bidResults.get("bidStatus")) {
			case "BID_IS_TOO_LOW":
				throw new BidIsTooLowException(bidResults.get("bidStatusMessage"));
				
			case "NEW_HIGH_BIDDER_RESERVE_MET":
				throw new NewHighBidderException(bidResults.get("bidStatusMessage"));
						
			case "NEW_HIGH_BIDDER_RESERVE_NOT_MET":
				throw new ReserveNotMetException(bidResults.get("bidStatusMessage"));
						
			case "IMMEDIATELY_OUTBID_RESERVE_MET":
				throw new ImmediateOutbidException(bidResults.get("bidStatusMessage"));
				
			case "IMMEDIATELY_OUTBID_RESERVE_NOT_MET":
				throw new ImmediateOutbidException(bidResults.get("bidStatusMessage"));
				
			default:
				throw new UnhandledUseCaseException(bidResults.get("bidStatusMessage"));
		}
		
	}
	
}