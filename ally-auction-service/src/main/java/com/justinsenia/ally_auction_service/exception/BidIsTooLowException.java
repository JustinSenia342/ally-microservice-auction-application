package com.justinsenia.ally_auction_service.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "Bid too low, please bid higher")
public class BidIsTooLowException extends Exception{

	private static final long serialVersionUID = 1L;
	
	public BidIsTooLowException(String message) {
		super(message);
	}
}
