package com.justinsenia.ally_orchestration_service.bean;

import lombok.Data;

@Data
public class BidBean {

	private long auctionItemId;
	
	private double maxAutoBidAmount;
	
	private String bidderName;
	
}
