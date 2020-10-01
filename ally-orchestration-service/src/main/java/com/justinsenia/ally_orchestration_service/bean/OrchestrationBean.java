package com.justinsenia.ally_orchestration_service.bean;

import java.util.Date;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
public class OrchestrationBean {

	// variables used to store lower level microservice data
	private @Getter @Setter long auctionId;
	private @Getter @Setter String auctionTitle;
	private @Getter @Setter String auctionDescription;
	private @Getter @Setter String sellerName;
	private @Getter @Setter double reservePrice;
	private @Getter @Setter Date startDate;
	private @Getter @Setter Date endDate;
	private @Getter @Setter double startingBid;
	private @Getter @Setter double currentBid;
	private @Getter @Setter int highestBidderId;
	private @Getter @Setter String highestBidderUsername;
	private @Getter @Setter double maxAutoBidAmount;
	private @Getter @Setter long itemId;
	private @Getter @Setter String itemName;
	private @Getter @Setter String itemDescription;
	private @Getter @Setter int port;

	
	//variables used to store internally modified data
	
	
	// No args constructor supplied by LOMBOK
	
	// All arg constructor
	public OrchestrationBean(long auctionId, String auctionTitle, String auctionDescription, String sellerName,
			double reservePrice, Date startDate, Date endDate, double startingBid, double currentBid,
			int highestBidderId, String highestBidderUsername, double maxAutoBidAmount, long itemId, String itemName,
			String itemDescription) {
		super();
		this.auctionId = auctionId;
		this.auctionTitle = auctionTitle;
		this.auctionDescription = auctionDescription;
		this.sellerName = sellerName;
		this.reservePrice = reservePrice;
		this.startDate = startDate;
		this.endDate = endDate;
		this.startingBid = startingBid;
		this.currentBid = currentBid;
		this.highestBidderId = highestBidderId;
		this.highestBidderUsername = highestBidderUsername;
		this.maxAutoBidAmount = maxAutoBidAmount;
		this.itemId = itemId;
		this.itemName = itemName;
		this.itemDescription = itemDescription;
	}
	
}
