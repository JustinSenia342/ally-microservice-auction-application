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
import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;

import org.hibernate.envers.Audited;
import org.springframework.beans.factory.annotation.Value;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

//@JsonIgnoreProperties(value = {
//		"maxAutoBidAmount"
//	})

@Entity
@Table(name = "auction")
@JsonInclude(JsonInclude.Include.NON_NULL)
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class Auction{

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "auction_item_id", nullable = false, unique = true,
			columnDefinition = "BIGINT")
	private long auctionItemId;
	
	@Audited
	@Column(name = "current_bid", precision = 14, scale = 2,
			columnDefinition = "DECIMAL")
	@Value("0.00")
	@DecimalMin("0.00")
	@DecimalMax("999999999999.99")
	private BigDecimal currentBid;
	
	@Audited
	@Column(name = "bidder_name", length = 50,
			columnDefinition = "VARCHAR(50)")
	@Value("")
	private String bidderName;
	
	@Audited
	@Column(name = "max_auto_bid_amount", precision = 14, scale = 2,
			columnDefinition = "DECIMAL")
	@Value("0.00")
	@DecimalMin("0.00")
	@DecimalMax("999999999999.99")
	private BigDecimal maxAutoBidAmount;
	
	@Column(name = "reserve_price", nullable = false, precision = 14, scale = 2,
			columnDefinition = "DECIMAL")
	@DecimalMin("0.00")
	@DecimalMax("999999999999.99")
	private BigDecimal reservePrice;
	
	@OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL, optional = false)
    @JoinColumn(name = "item", nullable = false, columnDefinition = "BIGINT")
    private Item item;
	
}
