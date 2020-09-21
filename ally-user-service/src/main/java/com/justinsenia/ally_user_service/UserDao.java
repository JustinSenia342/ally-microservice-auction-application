package com.justinsenia.ally_user_service;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

//@NoArgsConstructor
//@RequiredArgsConstructor
@Entity // This tells Hibernate to make a table out of this class
public class UserDao {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Getter @Setter
	private Integer userId;
	
	@Getter @Setter
	private String userName;
	
	@Getter @Setter
	private String userPass;
	
	@JsonInclude()
	@Transient
	@Getter @Setter
	private int port;
	

}


/*
	public UserDao() {
		
	}
	
	public UserDao(int userId, String userName, String userPass) {
		super();
		this.userId = userId;
		this.userName = userName;
		this.userPass = userPass;
	}
*/