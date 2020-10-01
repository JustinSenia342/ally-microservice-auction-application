package com.justinsenia.ally_orchestration_service.bean;

import java.util.Date;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

public class AuctionBean {

	// variables used to store lower level microservice data
	private @Getter @Setter long auctionItemId;
	private @Getter @Setter double currentBid;
	private @Getter @Setter String bidderName;
	private @Getter @Setter double reservePrice;
	private @Getter @Setter String itemId;
	private @Getter @Setter String description;
	

	
	//variables used to store internally modified data
	
	
	// No args constructor supplied by LOMBOK
	public AuctionBean() {
		
	}
	
	// All arg constructor
	public AuctionBean(long auctionItemId, double currentBid, String bidderName, 
			double reservePrice, String itemId, String description) {
		super();
		this.auctionItemId = auctionItemId;
		this.currentBid = currentBid;
		this.bidderName = bidderName;
		this.reservePrice = reservePrice;
		this.itemId = itemId;
		this.description = description;
	}
	
}
