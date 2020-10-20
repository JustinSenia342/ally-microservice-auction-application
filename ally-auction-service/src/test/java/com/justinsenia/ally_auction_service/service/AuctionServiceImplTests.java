package com.justinsenia.ally_auction_service.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import com.justinsenia.ally_auction_service.model.Auction;
import com.justinsenia.ally_auction_service.model.Bid;
import com.justinsenia.ally_auction_service.model.Item;
import com.justinsenia.ally_auction_service.repository.AuctionRepository;


@ExtendWith(MockitoExtension.class)
public class AuctionServiceImplTests {

	// Mocking Repository
	private AuctionRepository auctionRepository = Mockito.mock(AuctionRepository.class);
	
	// Declaring objects to be initialized before each test to maintain test independence
	// and to avoid code repetition
	private AuctionService auctionService;
	private Item item;
	private Auction auction;
	
	
	// Constructs new AuctionServiceImpl, Item, and Auction before each Test
	// In order to keep Tests independent from each other
	@BeforeEach
	void initUseCase() {
		auctionService = new AuctionServiceImpl(auctionRepository);
		
		item = new Item(1L, "Test-ItemId", "Test-Description");
		auction = new Auction(1L, BigDecimal.valueOf(10.00),
			"Test-BidderName", BigDecimal.valueOf(50.00),
			BigDecimal.valueOf(25.00), item);
	}
	

	
	// ------------------------------------------------------------------------------
	// Optional<Auction> findById(Long id) [Tests: 2]
	// ------------------------------------------------------------------------------
	// (1) Test if findById(Long) is being called correctly
	// (2) Test if IllegalArgumentException (null) is thrown when id argument is null
	// ------------------------------------------------------------------------------
	
	// (1) Test if findById(Long) is being called correctly
	@Test
	void findById_IdArgumentIsValid_ReturnsOptionalAuction() {
		
		// ARRANGE
		final Optional<Auction> optionalAuction = Optional
			.ofNullable(auction);
		when(auctionRepository.findById(any(Long.class)))
			.thenReturn(optionalAuction);
		
		// ACT
		Optional<Auction> returnedAuction = auctionService.findById(1l);
		
		// ASSERT
		assertTrue(returnedAuction.isPresent());
	}
	
	// (2) Test if IllegalArgumentException is thrown when id argument is null
	@Test
	void findById_IdArgumentIsNull_ThrowsIllegalArgumentException() {
		
		// ARRANGE
		Long longObjectWithNullValue = null;
		when(auctionRepository.findById(longObjectWithNullValue))
			.thenThrow(IllegalArgumentException.class);
		
		// ACT / ASSERT
		assertThrows(IllegalArgumentException.class,
			() -> {auctionService.findById(longObjectWithNullValue);});
	}
	
	
	
	// -----------------------------------------------------
	// void save(Auction auction) [Tests: 1]
	// -----------------------------------------------------
	// (1) Test if save(auction) method is called correctly
	// -----------------------------------------------------
	
	// (1) Test if save(auction) method is called correctly
	@Test
	void save_AuctionArgumentIsValid_CallsSaveAuction() {
		
		// ARRANGE
		
		// ACT
		auctionService.save(auction);
		
		// ASSERT
		verify(auctionRepository).save(auction);
	}
	
	
	
	// --------------------------------------------------
	// List<Auction> findAll() [Tests: 1]
	// --------------------------------------------------
	// (1) Test if findAll() method is called
	// --------------------------------------------------
	
	// (1) Test if findAll() method is called
	@Test
	void findAll_AuctionsExistInDatabase_ReturnsAuctionList() {
		
		// ARRANGE
		List<Auction> auctionList = new ArrayList<>();
		auctionList.add(auction);
		when(auctionRepository.findAll()).thenReturn(auctionList);
		
		// ACT
		List<Auction> returnedAuctionList = auctionService.findAll();
		
		// ASSERT
		assertThat(returnedAuctionList).isNotNull();
	}
	
	
	
	// --------------------------------------------------------------------------------
	// void makeBid(Auction auction, Bid bid) [Tests: 2 (parameterized), (1) is optional]
	// --------------------------------------------------------------------------------
	// (1)(Optional) Test if correct bidStatus is returned from... 
	// ...String getBidStatus(Auction auction, Bid bid) implementation detail method
	// (2) Test to make sure each possible execution path calls save(auction)
	// --------------------------------------------------------------------------------
	
	// (1)(Optional) Test if correct bidStatus is returned from... 
	// ...String getBidStatus(Auction auction, Bid bid) implementation detail method
	@Disabled("Disabled: Used for testing implementation details, "
			+ "prone to break if those details change.")
	@ParameterizedTest(name = "bidStatus Returned: {5}")
	@CsvSource({"1, 5.00, 100.00, 200.00, 150.00, BID_IS_TOO_LOW",
		"1, 300.00, 100.00, 200.00, 150.00, NEW_HIGH_BIDDER_RESERVE_MET",
		"1, 225.00, 100.00, 200.00, 250.00, NEW_HIGH_BIDDER_RESERVE_NOT_MET",
		"1, 175.00, 100.00, 200.00, 150.00, IMMEDIATELY_OUTBID_RESERVE_MET",
		"1, 175.00, 100.00, 200.00, 250.00, IMMEDIATELY_OUTBID_RESERVE_NOT_MET"})
	void makeBid_AuctionAndBidArgumentsAreValid_ReturnsBidStatusStrings
		(String id, double newMaxAutoBidAmount, double currentBid, 
		double oldMaxAutoBidAmount, double reservePrice, String expectedResult) {
		
		// ARRANGE
		auction.setAuctionItemId(Long.parseLong(id));
		auction.setCurrentBid(BigDecimal.valueOf(currentBid));
		auction.setMaxAutoBidAmount(BigDecimal.valueOf(oldMaxAutoBidAmount));
		auction.setReservePrice(BigDecimal.valueOf(reservePrice));
		
		// String auctionItemId,  String bidderName, BigDecimal maxAutoBidAmount
	    Bid bid = new Bid(id, "newBidderName", BigDecimal.valueOf(newMaxAutoBidAmount));
		
		// ACT
	    Map<String, String> returnedMap = auctionService.makeBid(auction, bid);
		
		// ASSERT
		assertEquals(expectedResult, returnedMap.get("bidStatus"));
	}
	
	// (2) Test to make sure each possible execution path calls save(auction)
	@ParameterizedTest(name = "Auction saved via {5} execution path")
	@CsvSource({"1, 300.00, 100.00, 200.00, 150.00, NEW_HIGH_BIDDER_RESERVE_MET",
		"1, 225.00, 100.00, 200.00, 250.00, NEW_HIGH_BIDDER_RESERVE_NOT_MET",
		"1, 175.00, 100.00, 200.00, 150.00, IMMEDIATELY_OUTBID_RESERVE_MET",
		"1, 175.00, 100.00, 200.00, 250.00, IMMEDIATELY_OUTBID_RESERVE_NOT_MET"})
	void makeBid_AuctionAndBidArgumentsAreValid_SavesAuctions
		(String id, double newMaxAutoBidAmount, double currentBid,
		double oldMaxAutoBidAmount, double reservePrice, String expectedResult) {
		
		// ARRANGE
		auction.setAuctionItemId(Long.parseLong(id));
		auction.setCurrentBid(BigDecimal.valueOf(currentBid));
		auction.setMaxAutoBidAmount(BigDecimal.valueOf(oldMaxAutoBidAmount));
		auction.setReservePrice(BigDecimal.valueOf(reservePrice));
		
		// Bid signature: String auctionItemId,  String bidderName, BigDecimal maxAutoBidAmount
	    Bid bid = new Bid(id, "newBidderName", BigDecimal.valueOf(newMaxAutoBidAmount));
		
		// ACT
	    auctionService.makeBid(auction, bid);
		
		// ASSERT
	    verify(auctionRepository).save(auction);
	}
	
}
