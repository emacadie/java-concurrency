package info.shelfunit.concurrency.venkatsbook.ch006.writeSkew;

import scala.concurrent.stm.Ref;
import scala.concurrent.stm.japi.STM;

// from Programming Concurrency on the JVM by Venkat Subramaniam

public class UsePortfolio {

    public static void main( final String[] args ) throws InterruptedException {
	final Portfolio portfolio = new Portfolio();
	int checkingBalance = portfolio.getCheckingBalance();
	int savingsBalance = portfolio.getSavingsBalance();

	System.out.println();
    } // end main



} // end class UsePortfolio
