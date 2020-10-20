package com.justinsenia.ally_auction_service.service;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.justinsenia.ally_auction_service.model.Auction;
import com.justinsenia.ally_auction_service.model.Bid;
import com.justinsenia.ally_auction_service.repository.AuctionRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuctionServiceImpl implements AuctionService {

	// Implemented with constructor-based DI design to make Unit testing faster
	private final AuctionRepository auctionRepository;
	
	/**
	 * Locates an Option<Auction> object from the database using the auction id
	 * 
	 * @param  id  id of the auction to be retrieved in the database
	 * @return     Optional<Auction> object which might be null
	 * 
	 */
	@Override
	public Optional<Auction> findById(Long id) throws IllegalArgumentException {
		if (id == null)
			throw new IllegalArgumentException("Auction not found on :: "+ id);
		
		return auctionRepository.findById(id);
	}
	
	/**
	 * Saves an Auction object to the database
	 * 
	 * @param  auction  Auction to be saved to the database
	 * 
	 */
	@Override
	public void save(Auction auction) {
		auctionRepository.save(auction);
	}
	
	/**
	 * Retrieves a list of all Auction objects from the database
	 * in the form of List<Auction>
	 * 
	 * @return  List<Auction> object containing a list of all auctions
	 * 
	 */
	@Override
	public List<Auction> findAll() {
		return auctionRepository.findAll();
	}

	/**
	 * Makes a bid on an existing auction in the database.
	 * <p>
	 * Logic implementation determines how bid will be handled
	 * in accordance with established global auction rules,
	 * saves or does not save according to the execution case,
	 * then returns an object of Map<String, String> to the 
	 * controller class to describe what it did and provide
	 * a details on what exception to throw in order to notify
	 * the bidder of results of their newly submitted bid.
	 * 
	 * @param  auction             Auction that is going to be Bid upon
	 * @param  bid                 Bid to be applied to the supplied Auction
	 * @return Map<String, String> Map containing bid status results
	 * 
	 */
	@Override
	public Map<String, String> makeBid(Auction auction, Bid bid) {
		
		// Used to update auction values if bid is valid
		BigDecimal newMaxAutoBidAmount = bid.getMaxAutoBidAmount();
		BigDecimal oldMaxAutoBidAmount = auction.getMaxAutoBidAmount();
		BigDecimal currentBid = auction.getCurrentBid();
		BigDecimal reservePrice = auction.getReservePrice();
		
		String newBidderName = bid.getBidderName();
		
		// Stores bidStatus and bidStatusMessage for return value
		Map<String, String> bidResults = new HashMap<>();
				
		
		// Used to determine how exception message will be formatted
		boolean isAuctionNew = currentBid.doubleValue() == 0.00 ? true : false;
		
		// Used to determine if bid is valid
		boolean isBidAmountValid = newMaxAutoBidAmount.doubleValue() >=
			currentBid.doubleValue() + 1.00 ? true : false;
		
		// Used to determine bid processing approach and message format
		boolean reservePriceIsMet = newMaxAutoBidAmount.doubleValue() >= 
			reservePrice.doubleValue() ? true : false;
			
		// Used to determine bid processing approach and message format
		boolean newMaxAutoBidIsGreater = newMaxAutoBidAmount.doubleValue() >= 
			oldMaxAutoBidAmount.doubleValue() + 1.00 ? true : false;
		
		
		// Determines processing approach for bid based on argument values
		bidResults.put("bidStatus", getBidStatus(auction, bid,
			isBidAmountValid, reservePriceIsMet, newMaxAutoBidIsGreater));
		
		// Builds message to describe bid processing results
		bidResults.put("bidStatusMessage", buildBidStatusMessage(auction, bid,
			isAuctionNew, isBidAmountValid, reservePriceIsMet,
			newMaxAutoBidIsGreater, bidResults.get("bidStatus")));
		
		
		
		// If bid is invalid (bid price less than current auction price + $1.00):
		// Do not update or save auction
		if (bidResults.get("bidStatus").equals("BID_IS_TOO_LOW")) {
		
		}
		
		// If bid is valid (bid price greater than or equal to 
		// the current auction price + $1.00): continue processing bid
		
		
		// If bid makes user the new high bidder and the reserve is met
		// Set auction values, save auction
		else if (bidResults.get("bidStatus").equals("NEW_HIGH_BIDDER_RESERVE_MET")) {
			
			auction.setCurrentBid(oldMaxAutoBidAmount.add(BigDecimal.valueOf(1.00)));
			auction.setMaxAutoBidAmount(newMaxAutoBidAmount);
			auction.setBidderName(newBidderName);
			
	        save(auction);  
		}
		
		// If bid makes user the new high bidder and the reserve is not met
		// Set auction values, save auction
		else if (bidResults.get("bidStatus").equals("NEW_HIGH_BIDDER_RESERVE_NOT_MET")) {
			auction.setCurrentBid(newMaxAutoBidAmount);
			auction.setMaxAutoBidAmount(newMaxAutoBidAmount);
			auction.setBidderName(newBidderName);

	        save(auction);
		}
		
		// If bid does not make user the new high bidder but the reserve is met
		// Set auction values, save auction
		else if (bidResults.get("bidStatus").equals("IMMEDIATELY_OUTBID_RESERVE_MET")) {
			auction.setCurrentBid(newMaxAutoBidAmount);

	        save(auction);
		}
		
		// If bid does not make user the new high bidder and the reserve is not met
		// Set auction values, save auction
		else if (bidResults.get("bidStatus").equals("IMMEDIATELY_OUTBID_RESERVE_NOT_MET")) {
			auction.setCurrentBid(newMaxAutoBidAmount);

	        save(auction);
		}
		
		// Unhandled use case
		// Do not update or save auction
		else {
			
		}
		
		// Return bidResults containing the bidStatus and bidStatusMessage
		return bidResults;
		
	}
	
	
	/*
	 *  -Implementation method-
	 *  
	 *  Returns String identifier which determines how bid will get
	 *  processed. used in the makeBid(Auction auction, Bid bid) method.
	 */
	private String getBidStatus(Auction auction, Bid bid, boolean isBidAmountValid,
		boolean reservePriceIsMet, boolean newMaxAutoBidIsGreater) {
			
		if (isBidAmountValid == false)
			return "BID_IS_TOO_LOW";
		else if ( reservePriceIsMet == true && newMaxAutoBidIsGreater == true )
			return "NEW_HIGH_BIDDER_RESERVE_MET";
		else if ( reservePriceIsMet == false && newMaxAutoBidIsGreater == true )
			return "NEW_HIGH_BIDDER_RESERVE_NOT_MET";
		else if ( reservePriceIsMet == true && newMaxAutoBidIsGreater == false )
			return "IMMEDIATELY_OUTBID_RESERVE_MET";
		else if ( reservePriceIsMet == false && newMaxAutoBidIsGreater == false )
			return "IMMEDIATELY_OUTBID_RESERVE_NOT_MET";
		else
			return "UNHANDLED_USE_CASE";
	}
	
	
	/*
	 *  -Implementation method-
	 *  
	 *  Builds message to describe the bid processing results.
	 *  Used in the makeBid(Auction auction, Bid bid) method.
	 */
	private String buildBidStatusMessage(Auction auction, Bid bid,
		boolean isAuctionNew, boolean isBidAmountValid, boolean reservePriceIsMet, 
		boolean newMaxAutoBidIsGreater, String bidStatus) {
		
		Long auctionItemId = auction.getAuctionItemId();
		String itemId = auction.getItem().getItemId();
		String oldBidderName = auction.getBidderName();
		String newBidderName = bid.getBidderName();
		
		StringBuilder stringBuilder = new StringBuilder();
		
		// If new bid value is invalid (less than current auction price + $1.00):
		// Then return invalid bid amount message
		if (isBidAmountValid == false)
			return "Submitted bid amount is invalid: "
				+ "Newly submitted bid is less than the current bid";

		// If new bid value is valid (greater than or equal to current auction price + $1.00):
		// Continue constructing bidStatusMessage String
		if (isAuctionNew == true && newMaxAutoBidIsGreater == true) {
			stringBuilder.append("Bid has been placed! "
			+ newBidderName + " Is the new high bidder for auction item #" 
			+ auctionItemId + " ( " + itemId + " )");
		}
		else if (isAuctionNew == false && newMaxAutoBidIsGreater == true) {
			stringBuilder.append("Bid has been placed! " + oldBidderName + " Has been outbid! "
			+ newBidderName + " Is the new high bidder for auction item #" 
			+ auctionItemId + " ( " + itemId + " )");
		}
		else if (newMaxAutoBidIsGreater == false) {
			stringBuilder.append("Bid has been placed! But " + oldBidderName + " Has not been outbid! "
			+ oldBidderName + " Is still the high bidder for auction item #"
			+ auctionItemId + " ( " + itemId + " )");
		}
		
		// Appends details about reserve price if it still hasn't been met for the auction
		if (reservePriceIsMet == true) {
			stringBuilder.append(".");
		}
		else if (reservePriceIsMet == false) {
			stringBuilder.append(", and the reserve price still hasn't been met.");
		}
		
		// return String built with StringBuilder or general string if there was an issue
		if (stringBuilder.length() > 0)
			return stringBuilder.toString();
		else
			return "Unhandled Use-Case";
		
	}
	
}
