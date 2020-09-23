package com.justinsenia.ally_user_service.model;

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


// User model DAO Class

@Entity
@Table(name = "users")
@EntityListeners(AuditingEntityListener.class)
public class User {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "id", nullable = false)
	private long id;
	
	@Column(name = "username", nullable = false, unique = true)
	@Getter @Setter
	private String username;
	
	@Column(name = "password", nullable = false)
	@Getter @Setter
	private String password;
	
	@Column(name = "email_address", nullable = false)
	@Getter @Setter
	private String emailAddress;
	
	@Column(name = "role", nullable = false)
	@Getter @Setter
	private String role;
	
	@Column(name = "enabled", nullable = false)
	@Getter @Setter
	private boolean enabled;
	
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
