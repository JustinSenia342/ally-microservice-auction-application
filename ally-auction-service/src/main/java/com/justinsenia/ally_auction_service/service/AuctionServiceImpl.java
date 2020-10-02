package com.justinsenia.ally_auction_service.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.justinsenia.ally_auction_service.exception.ResourceNotFoundException;
import com.justinsenia.ally_auction_service.model.Auction;
import com.justinsenia.ally_auction_service.repository.AuctionRepository;

@Service
public class AuctionServiceImpl implements AuctionService {

	@Autowired
	private AuctionRepository auctionRepository;
	
	
	@Override
	public Optional<Auction> findById(Long id) throws IllegalArgumentException {
		if (id == null)
			throw new IllegalArgumentException("Auction not found on :: "+ id);
		
		return auctionRepository.findById(id);
	}
	
	@Override
	public void save(Auction auction) {
		auctionRepository.save(auction);
	}
	
	@Override
	public List<Auction> findAll() {
		return auctionRepository.findAll();
	}

	@Override
	public void delete(Long id) throws IllegalArgumentException {
		if (id == null)
		throw new IllegalArgumentException("Auction not found on :: "+ id);
		
		auctionRepository.deleteById(id);
	}
	
}