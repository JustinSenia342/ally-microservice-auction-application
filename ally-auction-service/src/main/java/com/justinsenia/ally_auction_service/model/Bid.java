package com.justinsenia.ally_auction_service.model;

import java.math.BigDecimal;

import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Bid {

	@Size (min = 1)
	private String auctionItemId;
	
	@DecimalMin("0.00")
	@DecimalMax("999999999999.99")
	private BigDecimal maxAutoBidAmount;
	
	@Size (min = 1, max = 50)
	private String bidderName;
	
}
