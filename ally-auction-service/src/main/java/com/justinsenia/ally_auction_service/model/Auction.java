package com.justinsenia.ally_auction_service.model;


import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Getter;
import lombok.Setter;


// Auction model DAO Class

@Entity
@Table(name = "auctions")
@EntityListeners(AuditingEntityListener.class)
public class Auction {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "id", nullable = false)
	private long id;
	
	@Column(name = "auction_title", nullable = false)
	@Getter @Setter
	private String auctionTitle;
	
	@Column(name = "auction_description", nullable = false)
	@Getter @Setter
	private String auctionDescription;
	
	@Column(name = "seller_name", nullable = false)
	@Getter @Setter
	private String sellerName;
	
	@Column(name = "reserve_price", nullable = false)
	@Getter @Setter
	private double reservePrice;
	
	@Column(name = "start_date", nullable = false)
	@Getter @Setter
	@CreatedDate
	private Date startDate;
	
	@Column(name = "end_date", nullable = false)
	@Getter @Setter
	private Date endDate;
	
	@Column(name = "starting_bid", nullable = false)
	@Getter @Setter
	private double startingBid;
	
	@Column(name = "current_bid", nullable = false)
	@Getter @Setter
	private double currentBid;
	
	@Column(name = "highest_bidder_id", nullable = false)
	@Getter @Setter
	private int highestBidderId;
	
	@Column(name = "highest_bidder_username", nullable = false)
	@Getter @Setter
	private String highestBidderUsername;
	
	@Column(name = "max_auto_bid_amount", nullable = false)
	@Getter @Setter
	private double maxAutoBidAmount;
	
	@Column(name = "created_at", nullable = false)
	@Getter @Setter
	@CreatedDate
	private Date createdAt;
	
	@Column(name = "created_by", nullable = false)
	@Getter @Setter
	@CreatedBy
	private String createdBy;
	
	@Column(name = "updated_at", nullable = false)
	@Getter @Setter
	@LastModifiedDate
	private Date updatedAt;
	
	@Column(name = "updated_by", nullable = false)
	@Getter @Setter
	@LastModifiedBy
	private String updatedBy;
	
	@JsonInclude()
	@Transient
	private int port;

}
