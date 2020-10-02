package com.justinsenia.ally_auction_service.model;

import java.math.BigDecimal;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.envers.Audited;
import org.springframework.beans.factory.annotation.Value;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Getter;
import lombok.Setter;

@JsonIgnoreProperties(value = {
		"maxAutoBidAmount"
	})

@Entity
@Table(name = "auction")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Auction{

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "auction_item_id", nullable = false, unique = true,
			columnDefinition = "BIGINT")
	@Getter @Setter
	private long auctionItemId;
	
	@Audited
	@Column(name = "current_bid", precision = 14, scale = 2,
			columnDefinition = "DECIMAL")
	@Value("0.00")
	@DecimalMin("0.00")
	@Getter @Setter
	@DecimalMax("999999999999.99")
	private BigDecimal currentBid;
	
	@Audited
	@Column(name = "bidder_name", length = 50,
			columnDefinition = "VARCHAR(50)")
	@Value("")
	@Getter @Setter
	private String bidderName;
	
	@Audited
	@Column(name = "max_auto_bid_amount", precision = 14, scale = 2,
			columnDefinition = "DECIMAL")
	@Value("Default 0.00")
	@DecimalMin("0.00")
	@DecimalMax("999999999999.99")
	@Getter @Setter
	private BigDecimal maxAutoBidAmount;
	
	@Column(name = "reserve_price", nullable = false, precision = 14, scale = 2,
			columnDefinition = "DECIMAL")
	@DecimalMin("0.00")
	@DecimalMax("999999999999.99")
	@Getter @Setter
	private BigDecimal reservePrice;
	
	@OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL, optional = false)
    @JoinColumn(name = "item", nullable = false, columnDefinition = "BIGINT")
	@Getter @Setter
    private Item item;
	
	
	public Auction() {
		
	}

	public Auction(long auctionItemId, BigDecimal currentBid, String bidderName, BigDecimal maxAutoBidAmount,
			BigDecimal reservePrice, Item item) {
		super();
		this.auctionItemId = auctionItemId;
		this.currentBid = currentBid;
		this.bidderName = bidderName;
		this.maxAutoBidAmount = maxAutoBidAmount;
		this.reservePrice = reservePrice;
		this.item = item;
	}

}
