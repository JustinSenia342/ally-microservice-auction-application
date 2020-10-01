package com.justinsenia.ally_orchestration_service.bean;

import java.util.Date;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
public class ItemBean {

	// variables used to store lower level microservice data
	private @Getter @Setter long id;
	private @Getter @Setter String itemName;
	private @Getter @Setter String itemDescription;
	private @Getter @Setter Date createdAt;
	private @Getter @Setter String createdBy;
	private @Getter @Setter Date updatedAt;
	private @Getter @Setter String updatedBy;
	private @Getter @Setter int port;
	
	
	//variables used to store internally modified data
	
	
	// No args constructor supplied by LOMBOK
		
	// All arg constructor
	public ItemBean(long id, String itemName, String itemDescription, Date createdAt, String createdBy,
			Date updatedAt, String updatedBy) {
		super();
		this.id = id;
		this.itemName = itemName;
		this.itemDescription = itemDescription;
		this.createdAt = createdAt;
		this.createdBy = createdBy;
		this.updatedAt = updatedAt;
		this.updatedBy = updatedBy;
	}
}
