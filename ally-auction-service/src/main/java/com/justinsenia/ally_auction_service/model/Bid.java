package com.justinsenia.ally_auction_service.model;

import java.math.BigDecimal;

import lombok.Data;

@Data
public class Bid {

	private String auctionItemId;
	private BigDecimal maxAutoBidAmount;
	private String bidderName;
	
}
