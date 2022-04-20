package com.bank.domain;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.bank.exception.BalanceInsufficientException;
import com.bank.utilities.DateFormat;

public class Account {
	private String rib;
	private BigDecimal balance;
	private DateFormat dateformat;

	private List<Transaction> transactions = new ArrayList<Transaction>();
	private Printer printer;

	public Account(String rib, BigDecimal balance, DateFormat dateformat, Printer printer) {
		this.rib = rib;
		this.balance = balance;
		this.printer = printer;
		this.dateformat = dateformat;
	}

	public String getRib() {
		return rib;
	}

	public void setRib(String rib) {
		this.rib = rib;
	}

	public BigDecimal getBalance() {
		return balance;
	}

	public void setBalance(BigDecimal balance) {
		this.balance = balance;
	}

	public List<Transaction> getTransactions() {
		return transactions;
	}

	/**
	 * Method to deposit amount
	 * 
	 * @param amount
	 * @param date
	 */
	public void deposit(BigDecimal amount, LocalDate date) {
		if (amount.compareTo(BigDecimal.ZERO) < 0)
			throw new IllegalArgumentException("amount must be Positive");
		else {
			balance = balance.add(amount);
			Transaction transaction = new Transaction(rib, dateformat.dateToString(date), amount, "deposit", balance);
			transactions.add(0, transaction);

		}
	}

	/**
	 * Method to withdrwal amount
	 * @param amount
	 * @param date
	 * @throws BalanceInsufficientException
	 */
	public void withdrawal(BigDecimal amount, LocalDate date) throws BalanceInsufficientException {
		if (amount.compareTo(BigDecimal.ZERO) < 0)
			throw new IllegalArgumentException("amount must be Positive ");
		else if (amount.compareTo(balance) > 0)
			throw new BalanceInsufficientException("ERROR : insufficient balance");
		else {
			balance = balance.subtract(amount);
			Transaction transaction = new Transaction(rib, dateformat.dateToString(date), amount.negate(), "withdrawal",
					balance);
			transactions.add(0, transaction);
		}
	}

}
