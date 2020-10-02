package com.justinsenia.ally_auction_service.exception;

public class UnhandledUseCaseException extends Exception{

	private static final long serialVersionUID = 1L;
	
	public UnhandledUseCaseException(String message) {
		super(message);
	}
}