package com.justinsenia.ally_auction_service.model;

import java.math.BigDecimal;

import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class Bid {

	@Size (min = 1)
	private String auctionItemId;
	
	@Size (min = 1, max = 50)
	private String bidderName;
	
	@DecimalMin("0.00")
	@DecimalMax("999999999999.99")
	private BigDecimal maxAutoBidAmount;
	
}
