package com.justinsenia.ally_auction_service.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Getter;
import lombok.Setter;

@JsonIgnoreProperties(value = {
		"item"
	})

@Entity
@Table(name = "item")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Item{
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "item", nullable = false, unique = true,
			columnDefinition = "BIGINT")
	@Getter @Setter
	private long item;
	
	@Column(name = "item_id", nullable = false, length = 100,
			columnDefinition = "VARCHAR(100)")
	@Size ( min = 1, max = 100)
	@Getter @Setter
	private String itemId;
	
	@Column(name = "description", nullable = false,
			columnDefinition="TEXT")
	@Size ( min = 1, max = 1000)
	@Getter @Setter
	private String description;

	
	public Item() {
		
	}

	public Item(long item, String itemId, String description) {
		super();
		this.item = item;
		this.itemId = itemId;
		this.description = description;
	}
	
}
