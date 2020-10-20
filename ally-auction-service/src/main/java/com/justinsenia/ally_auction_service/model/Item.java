package com.justinsenia.ally_auction_service.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@JsonIgnoreProperties(value = {
		"item"
	})

@Entity
@Table(name = "item")
@JsonInclude(JsonInclude.Include.NON_NULL)
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class Item{
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "item", nullable = false, unique = true,
			columnDefinition = "BIGINT")
	private long item;
	
	@Column(name = "item_id", nullable = false, length = 100,
			columnDefinition = "VARCHAR(100)")
	@Size ( min = 1, max = 100)
	private String itemId;
	
	@Column(name = "description", nullable = false,
			columnDefinition="TEXT")
	@Size ( min = 1, max = 1000)
	private String description;
	
}
