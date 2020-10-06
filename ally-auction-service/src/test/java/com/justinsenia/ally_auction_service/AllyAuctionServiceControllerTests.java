package com.justinsenia.ally_auction_service;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Size;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.justinsenia.ally_auction_service.exception.BidIsTooLowException;
import com.justinsenia.ally_auction_service.exception.ImmediateOutbidException;
import com.justinsenia.ally_auction_service.exception.NewHighBidderByDefaultException;
import com.justinsenia.ally_auction_service.exception.NewHighBidderException;
import com.justinsenia.ally_auction_service.exception.ReserveNotMetException;
import com.justinsenia.ally_auction_service.exception.ResourceNotFoundException;
import com.justinsenia.ally_auction_service.exception.UnhandledUseCaseException;
import com.justinsenia.ally_auction_service.model.Auction;
import com.justinsenia.ally_auction_service.model.Bid;
import com.justinsenia.ally_auction_service.model.Item;
import com.justinsenia.ally_auction_service.repository.AuctionRepository;
import com.justinsenia.ally_auction_service.service.AuctionService;
import com.justinsenia.ally_auction_service.service.AuctionServiceImpl;

import lombok.Getter;
import lombok.Setter;

@ExtendWith(MockitoExtension.class)
public class AllyAuctionServiceControllerTests {

	// Controller Test Outline
	
	// POST /auctionItems ************************************************
    // Creates a new auction, returns the newly created auction Id in Json Format
    
	// GET /auctionItems *************************************************
    // Returns list of all auctions
	
	// GET /auctionItems/{auctionItemId} *********************************
	// Returns a single auction
		// Sub-tests
		// -ResourceNotFoundException
	
	// POST /bids ********************************************************
	// Updates persisted database values with new bid information
		// Sub-tests
		// -ResourceNotFoundException
		// -ImmediateOutbidException
		// -ReserveNotMetException
		// -UnhandledUseCaseException
		// -NewHighBidderException
		// -BidIsTooLowException
		// -NewHighBidderByDefaultException
	
	
	// Models and quick initialization copy/paste shortcuts **************
    // long item, String itemId, String description
    //final Item item = new Item(1L, "Test-ItemId", "Test-Description");
    
    // long auctionItemId, BigDecimal currentBid, String bidderName, BigDecimal maxAutoBidAmount, BigDecimal reservePrice, Item item
    //final Auction auction = new Auction(1L, BigDecimal.valueOf(10.00), "Test-BidderName", BigDecimal.valueOf(50.00), BigDecimal.valueOf(25.00), item);
    
    // String auctionItemId, BigDecimal maxAutoBidAmount, String bidderName
    //final Bid bid = new Bid("1", BigDecimal.valueOf(100.00), "Test-BidderName");
    

    
}
