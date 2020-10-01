package com.justinsenia.ally_auction_service.exception;

public class BidIsTooLowException extends Exception{

	private static final long serialVersionUID = 1L;
	
	public BidIsTooLowException(String message) {
		super(message);
	}
}
