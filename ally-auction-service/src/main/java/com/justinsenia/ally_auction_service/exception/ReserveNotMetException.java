package com.justinsenia.ally_auction_service.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

//@ResponseStatus(value = HttpStatus.RESERVE_NOT_MET, reason="To show an example of a custom message")
public class ReserveNotMetException extends Exception{

	private static final long serialVersionUID = 1L;
	
	public ReserveNotMetException(String message) {
		super(message);
	}
}
