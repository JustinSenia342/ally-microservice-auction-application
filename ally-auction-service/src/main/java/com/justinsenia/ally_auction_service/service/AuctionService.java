package com.justinsenia.ally_auction_service.service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import com.justinsenia.ally_auction_service.model.Auction;
import com.justinsenia.ally_auction_service.model.Bid;

public interface AuctionService {

	Optional <Auction> findById(Long id) throws IllegalArgumentException;
	
	void save(Auction auction);
	
	List <Auction> findAll();

	Map<String, String> makeBid(Auction auction, Bid bid);
	
}
