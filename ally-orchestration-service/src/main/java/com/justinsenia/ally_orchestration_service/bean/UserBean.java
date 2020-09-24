package com.justinsenia.ally_orchestration_service.bean;

import java.util.Date;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
public class UserBean {

	// variables used to store external service data

	private @Setter @Getter long id;
	private @Setter @Getter String username;
	private @Setter @Getter String password;
	private @Setter @Getter String emailAddress;
	private @Setter @Getter Date createdAt;
	private @Setter @Getter String createdBy;
	private @Setter @Getter Date updatedAt;
	private @Setter @Getter String updatedBy;
	private @Setter @Getter int port;
	
	//variables used to store internally modified data
	

	public UserBean(long id, String username, String password, String emailAddress, Date createdAt, String createdBy,
			Date updatedAt, String updatedBy) {
		super();
		this.id = id;
		this.username = username;
		this.password = password;
		this.emailAddress = emailAddress;
		this.createdAt = createdAt;
		this.createdBy = createdBy;
		this.updatedAt = updatedAt;
		this.updatedBy = updatedBy;
	}
	
}
