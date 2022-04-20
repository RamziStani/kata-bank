package com.bank.domain;

import com.bank.utilities.Console;

public class Printer {
	private static final String HEADER = "Operation | Date | Amount | Balance ";

	private Console console;

	public Printer(Console console) {
		this.console = console;
	}
}
