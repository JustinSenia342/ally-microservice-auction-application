package com.justinsenia.ally_auction_service.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;


import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.envers.Audited;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import lombok.Getter;
import lombok.Setter;

@JsonIgnoreProperties(value = {
		"maxAutoBidAmount"
	})

@Entity
@Table(name = "auction")
//@EntityListeners(AuditingEntityListener.class)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Auction{

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "auction_item_id", nullable = false)
	@Getter @Setter
	private long auctionItemId;
	
	//@Id
	//@GeneratedValue(strategy=GenerationType.IDENTITY)
	//@Column(name = "auction_item_id")
	//@Getter @Setter
	//private long auctionItemId;
	
	@Audited
	@Column(name = "current_bid", nullable = false, precision = 14, scale = 2)
	@Getter @Setter
	private BigDecimal currentBid;
	
	@Audited
	@Column(name = "bidder_name", length = 50)
	@Getter @Setter
	private String bidderName;
	
	@Audited
	@Column(name = "max_auto_bid_amount", precision = 14, scale = 2)
	@Getter @Setter
	private BigDecimal maxAutoBidAmount;
	
	@Column(name = "reserve_price", nullable = false, precision = 14, scale = 2)
	@Getter @Setter
	private BigDecimal reservePrice;
	
	//@Getter @Setter
	//@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	//private List<Item> item = new ArrayList<>();
	
	//@Getter @Setter
	//@OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL, optional = false)
	//private Item item = new Item();
	
	//***This one
	//@Column(name = "item", nullable = false)
	//@Getter @Setter
	//private Item item;
	//private Item item = new Item();
	
	@Getter @Setter
	@OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL, optional = false)
    @JoinColumn(name = "item", nullable = false)
    private Item item;
	
	//***Temp Alts
	//@Column(name = "item_id", nullable = false)
	//@Getter @Setter
	//private String itemId;
	
	//@Column(name = "description", nullable = false)
	//@Getter @Setter
	//private String description;
	
	
	//@Getter @Setter
	//@OneToOne(fetch = FetchType.LAZY, cascade=CascadeType.ALL, optional = false)
    //@JoinColumn(name = "item", nullable = false)
    //private Item item;
	

	/*
	@Column(name = "created_at", nullable = false)
	@Getter @Setter
	@CreatedDate
	private Date createdAt;
	
	@Column(name = "created_by")
	@Getter @Setter
	@CreatedBy
	private String createdBy;
	
	@Column(name = "updated_at", nullable = false)
	@Getter @Setter
	@LastModifiedDate
	private Date updatedAt;
	
	@Column(name = "updated_by")
	@Getter @Setter
	@LastModifiedBy
	private String updatedBy;
	
	*/
	
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

	/*public Auction(long auctionItemId, double currentBid, String bidderName, double maxAutoBidAmount, double reservePrice, String itemId,
			String description, Date createdAt, String createdBy, Date updatedAt, String updatedBy) {
		super();
		this.auctionItemId = auctionItemId;
		this.currentBid = currentBid;
		this.bidderName = bidderName;
		this.maxAutoBidAmount = maxAutoBidAmount;
		this.reservePrice = reservePrice;
		this.itemId = itemId;
		this.description = description;
		this.createdAt = createdAt;
		this.createdBy = createdBy;
		this.updatedAt = updatedAt;
		this.updatedBy = updatedBy;
	}*/
	



	
	/*
	public Auction(long auctionItemId, double currentBid, String bidderName, double reservePrice, Item item,
			Date createdAt, String createdBy, Date updatedAt, String updatedBy) {
		super();
		this.auctionItemId = auctionItemId;
		this.currentBid = currentBid;
		this.bidderName = bidderName;
		this.reservePrice = reservePrice;
		this.item = item;
		this.createdAt = createdAt;
		this.createdBy = createdBy;
		this.updatedAt = updatedAt;
		this.updatedBy = updatedBy;
	} 
	*/
	

}
