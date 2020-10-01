package com.justinsenia.ally_auction_service.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import lombok.Getter;
import lombok.Setter;

@JsonIgnoreProperties(value = {
		"item",
		"auction",
	    "createdAt",
	    "createdBy",
	    "updatedAt",
	    "updatedBy"
	})

@Entity
@Table(name = "item")
@EntityListeners(AuditingEntityListener.class)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Item{

	//@ManyToOne(fetch = FetchType.LAZY, targetEntity=Auction.class)
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "item")
	@Getter @Setter
	private long item;
	
	@Column(name = "item_id")
	@Getter @Setter
	private String itemId;
	
	@Column(name = "description")
	@Getter @Setter
	private String description;
	
	//@Getter @Setter
	//@ManyToOne(fetch = FetchType.LAZY, targetEntity=Auction.class)
    //private Auction auction;
	
	@Getter @Setter
	@OneToOne(fetch = FetchType.LAZY, targetEntity=Auction.class)
    private Auction auction;
	
	//@OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "item")
	//@Getter @Setter
	//private Auction auction;

	@Column(name = "created_at")
	@Getter @Setter
	@CreatedDate
	private Date createdAt;
	
	@Column(name = "created_by")
	@Getter @Setter
	@CreatedBy
	private String createdBy;
	
	@Column(name = "updated_at")
	@Getter @Setter
	@LastModifiedDate
	private Date updatedAt;
	
	@Column(name = "updated_by")
	@Getter @Setter
	@LastModifiedBy
	private String updatedBy;
	
	public Item() {
		
	}

	public Item(long item, String itemId, String description, Date createdAt, String createdBy, Date updatedAt,
			String updatedBy) {
		super();
		this.item = item;
		this.itemId = itemId;
		this.description = description;
		this.createdAt = createdAt;
		this.createdBy = createdBy;
		this.updatedAt = updatedAt;
		this.updatedBy = updatedBy;
	}
	
}
