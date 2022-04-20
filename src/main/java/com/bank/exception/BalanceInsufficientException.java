package com.bank.exception;

public class BalanceInsufficientException extends Exception {
	public BalanceInsufficientException(String message) {
		super(message);
	}
}
