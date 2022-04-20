package com.bank;

import static org.junit.Assert.assertEquals;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import com.bank.domain.Account;
import com.bank.domain.Printer;
import com.bank.domain.Transaction;
import com.bank.exception.BalanceInsufficientException;
import com.bank.utilities.DateFormat;

public class AccountTest {
	private Account account;

	private DateFormat dateformat;
	@Mock
	private Printer printer;

	@Before
	public void setUp() {
		dateformat = new DateFormat();
		account = new Account("1234", new BigDecimal(500), dateformat, printer);

	}

	/**
	 * Deposit success Test
	 */
	@Test
	public void deposit_success() {
		LocalDate depositDate = LocalDate.of(2022, 4, 22);
		account.deposit(new BigDecimal(50), depositDate);
		List<Transaction> transactions = account.getTransactions();
		assertEquals(transactions.size(), 1);
		assertEquals(transactions.get(0).getCurrentBalance(), BigDecimal.valueOf(550));
	}

	/**
	 * Deposit Faillure test
	 */
	@Test(expected = IllegalArgumentException.class)
	public void deposit_account_negative_amount() {
		account.setBalance(new BigDecimal(500));
		LocalDate depositDate = LocalDate.of(2022, 4, 22);
		account.deposit(new BigDecimal(50).negate(), depositDate);

	}
	/**
	 * withdrwal success test
	 * @throws BalanceInsufficientException
	 */
	@Test
	public void withdrawal() throws BalanceInsufficientException {
		LocalDate withdrawalDate = LocalDate.of(2020, 6, 24);
		account.withdrawal(new BigDecimal(50), withdrawalDate);
		List<Transaction> transactions = account.getTransactions();
		assertEquals(transactions.size(), 1);
		assertEquals(transactions.get(0).getCurrentBalance(),BigDecimal.valueOf(450));
	}
	/**
	 * withdrwal faillure test
	 * @throws BalanceInsufficientException
	 */
	@Test(expected = IllegalArgumentException.class)
	public void withdraw_account_negative_amount() throws BalanceInsufficientException {
		account.setBalance(new BigDecimal(500));
		LocalDate withdrawalDate = LocalDate.of(2020, 6, 24);
		account.withdrawal(new BigDecimal(600).negate(), withdrawalDate);
	}
}
