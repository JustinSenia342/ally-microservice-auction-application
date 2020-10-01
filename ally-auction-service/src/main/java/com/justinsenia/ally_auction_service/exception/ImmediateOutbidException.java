package com.justinsenia.ally_auction_service.exception;

public class ImmediateOutbidException extends Exception{

	private static final long serialVersionUID = 1L;
	
	public ImmediateOutbidException(String message) {
		super(message);
	}
}
