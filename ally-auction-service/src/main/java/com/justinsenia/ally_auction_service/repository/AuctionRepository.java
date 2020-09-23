package com.justinsenia.ally_auction_service.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

import com.justinsenia.ally_auction_service.model.Auction;


// Repository which extends a Jpa Repository using a User DAO Object 
@Repository 
public interface AuctionRepository extends JpaRepository<Auction, Long>{

}
