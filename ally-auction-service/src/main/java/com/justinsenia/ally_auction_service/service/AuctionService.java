package com.justinsenia.ally_auction_service.service;

import java.util.List;
import java.util.Optional;

import com.justinsenia.ally_auction_service.exception.ResourceNotFoundException;
import com.justinsenia.ally_auction_service.model.Auction;

public interface AuctionService {

	Optional <Auction> findById(Long id) throws IllegalArgumentException;
	
	void save(Auction auction);
	
	List <Auction> findAll();

	void delete(Long id) throws IllegalArgumentException;
	
}
