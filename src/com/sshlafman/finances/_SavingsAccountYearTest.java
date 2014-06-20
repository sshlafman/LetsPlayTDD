package com.sshlafman.finances;

import static org.junit.Assert.assertEquals;

import org.junit.*;

public class _SavingsAccountYearTest {
	
	@Test
	public void startingBalanceMatchesConstructor() {
		assertEquals(10000, newAccount().startingBalance());
	}

	@Test
	public void interestRateMatchesConstructor () {
		assertEquals(10, newAccount().interestRate());
	}
	
	@Test
	public void endingBalanceAppliesInterestRates() {
		assertEquals(11000, newAccount().endingBalance(25));
	}
	
	@Test
	public void nextYearsStartingBalanceEqualThisYearsEndingBalance()
	{
		SavingsAccountYear thisYear = newAccount();
		assertEquals(thisYear.endingBalance(25), thisYear.nextYear(25).startingBalance());
	}
	
	@Test
	public void thisYearsInterestRateEqualsNextYearsInterestRate() {
		SavingsAccountYear thisYear = newAccount();
		assertEquals(thisYear.interestRate(), thisYear.nextYear(25).interestRate() );
	}
	
	@Test
	public void withdrawingFundsOccursAtTheBeginningOfTheYear() {
		SavingsAccountYear year = new SavingsAccountYear(10000, 10);
		year.withdraw(1000);
		assertEquals(9900, year.endingBalance(25));
	}
	
	@Test
	public void mulitpleWithdrawalsInAYear () {
		SavingsAccountYear year = new SavingsAccountYear(10000, 10);
		year.withdraw(1000);
		year.withdraw(2000);
	    assertEquals(3000, year.totalWithdrawn());
	}
	
	@Test
	public void startingPrincipal () {
		SavingsAccountYear year = new SavingsAccountYear(10000, 3000, 10);
		assertEquals(3000, year.startingPrinticpal());
	}
	
	@Test
	public void endingPrincipal () {
		SavingsAccountYear year = new SavingsAccountYear(10000, 3000, 10);
		year.withdraw(2000);
		assertEquals("endingPrincipal", 1000, year.endingPrincipal());
	}
	
	@Test
	public void endingPrincipalNeverGoesBelowZero () {
		SavingsAccountYear year = new SavingsAccountYear(10000, 3000, 10);
		year.withdraw(4000);
		assertEquals("endingPrincipal", 0, year.endingPrincipal());
	
	}
	
	@Test
	public void capitalGainsWithdrawn() {
		SavingsAccountYear year = new SavingsAccountYear(10000, 3000, 10);
		year.withdraw(1000);
		assertEquals(0, year.capitalGainsWithdrawn());
		year.withdraw(3000);
		assertEquals(1000, year.capitalGainsWithdrawn());
	}
	
	@Test
	public void capitalGainsTaxIncurred_NeedsToCoverCapitalGainsWithdrawn_AND_theAdditionalCaiptalGainsWithdrawnToPayCapitalGainsTax() {
		SavingsAccountYear year = new SavingsAccountYear(10000, 3000, 10);
		year.withdraw(5000);
		assertEquals(2000, year.capitalGainsWithdrawn());
		assertEquals(666, year.capitalGainsTaxIncurred(25));
	}

	@Test
	public void capitalGainsTaxIsIncludedInendingBalance() {
		SavingsAccountYear year = new SavingsAccountYear(10000, 3000, 10);
		int amountWithdrawn = 5000;
		year.withdraw(amountWithdrawn);
		int expectedCapitalGainsTax = 666;
		assertEquals(expectedCapitalGainsTax, year.capitalGainsTaxIncurred(25));
		int expectedStartingBalanceAfterWithdrawls = 10000 - amountWithdrawn - expectedCapitalGainsTax;
		assertEquals((int)(expectedStartingBalanceAfterWithdrawls * 1.10), year.endingBalance(25));		
	}
	
	private SavingsAccountYear newAccount() {
		SavingsAccountYear account = new SavingsAccountYear(10000,10);
		return account;
	}

}
