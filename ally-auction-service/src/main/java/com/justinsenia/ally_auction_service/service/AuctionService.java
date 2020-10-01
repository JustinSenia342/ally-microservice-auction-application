package com.justinsenia.ally_auction_service.service;

import java.util.List;
import java.util.Optional;

import com.justinsenia.ally_auction_service.model.Auction;

public interface AuctionService {

	List <Auction> findAll();
	
	void save(Auction auction);
	
	Optional <Auction> findById(Long id);
	
	void delete(long id);
	
}
