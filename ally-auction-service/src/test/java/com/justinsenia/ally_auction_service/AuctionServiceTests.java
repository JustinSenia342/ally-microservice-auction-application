package com.justinsenia.ally_auction_service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.doReturn;
import static org.mockito.ArgumentMatchers.any;

import com.justinsenia.ally_auction_service.model.Auction;
import com.justinsenia.ally_auction_service.model.Item;
import com.justinsenia.ally_auction_service.repository.AuctionRepository;
import com.justinsenia.ally_auction_service.service.AuctionService;

@SpringBootTest
class AuctionServiceTests {

	@Autowired
	private AuctionService service;
	
	@MockBean
	private AuctionRepository repository;
	
	@Test
    @DisplayName("Test findById Success")
    void testFindById() {
        // Setup mock repository
		Item item = new Item(1l, "Test-itemId-01", "Test-description-01");
        Auction auction = new Auction(1l, BigDecimal.valueOf(0.00), "Test-bidderName-01", BigDecimal.valueOf(0.00), BigDecimal.valueOf(222.22), item);
        
        doReturn(Optional.of(auction)).when(repository).findById(1l);

        // Execute the service call
        Optional<Auction> returnedAuction = service.findById(1l);

        // Assert the response
        Assertions.assertTrue(returnedAuction.isPresent(), "Auction was not found");
        Assertions.assertSame(returnedAuction.get(), auction, "The auction returned was not the same as the mock");
    }

    @Test
    @DisplayName("Test findById Not Found")
    void testFindByIdNotFound() {
        // Setup mock repository
        doReturn(Optional.empty()).when(repository).findById(1l);

        // Execute the service call
        Optional<Auction> returnedAuction = service.findById(1l);

        // Assert the response
        Assertions.assertFalse(returnedAuction.isPresent(), "Auction should not be found");
    }

    @Test
    @DisplayName("Test findAll")
    void testFindAll() {
        // Setup mock repository
    	Item item01 = new Item(1l, "Test-itemId-01", "Test-description-01");
    	Auction auction01 = new Auction(1l, BigDecimal.valueOf(1.11), "Test-bidderName-01", BigDecimal.valueOf(1.11), BigDecimal.valueOf(111.11), item01);
        Item item02 = new Item(2l, "Test-itemId-02", "Test-description-02");
        Auction auction02 = new Auction(2l, BigDecimal.valueOf(2.22), "Test-bidderName-02", BigDecimal.valueOf(2.22), BigDecimal.valueOf(222.22), item02);
        doReturn(Arrays.asList(auction01, auction02)).when(repository).findAll();

        // Execute the service call
        List<Auction> auctions = service.findAll();

        // Assert the response
        Assertions.assertEquals(2, auctions.size(), "findAll should return 2 auctions");
    }

    @Test
    @DisplayName("Test save auction")
    void testSave() {
        // Setup mock repository
    	Item item = new Item(1l, "Test-itemId-01", "Test-description-01");
        Auction auction = new Auction(1l, BigDecimal.valueOf(0.00), "Test-bidderName-01", BigDecimal.valueOf(0.00), BigDecimal.valueOf(222.22), item);
        doReturn(auction).when(repository).save(any());

        // Execute the service call
        service.save(auction);
        
        Optional<Auction> returnedAuction = service.findById(1l);

        // Assert the response
        Assertions.assertNotNull(returnedAuction, "The saved auction should not be null");
    }

}
