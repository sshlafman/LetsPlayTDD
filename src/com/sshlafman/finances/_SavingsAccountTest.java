package com.sshlafman.finances;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class _SavingsAccountTest {

	@Test
	public void depositAndWithdrawal() {
		SavingsAccount account = new SavingsAccount();
		account.deposit(100);
		assertEquals("after deposit", 100, account.balance());
		account.withdraw(50);
		assertEquals("after withdrawal", 50, account.balance());
	}
	
	@Test
	public void negativeBalanceIsJustFine()
	{
		SavingsAccount account = new SavingsAccount();
		account.withdraw(75);
		assertEquals(-75, account.balance());
	}

}
