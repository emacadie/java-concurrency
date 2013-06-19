package info.shelfunit.concurrency.venkatsbook.ch006.writeSkew;

import java.util.concurrent.Executors;
import java.util.concurrent.ExecutorService;

// from Programming Concurrency on the JVM by Venkat Subramaniam

public class UsePortfolio {

    public static void main( final String[] args ) throws InterruptedException {
	final Portfolio portfolio = new Portfolio();
	int checkingBalance = portfolio.getCheckingBalance();
	int savingBalance = portfolio.getSavingsBalance();

	System.out.println("Checking balance is " + checkingBalance);
	System.out.println("Savings balance is " + savingBalance);
	System.out.println("Total balance is: " + (checkingBalance + savingBalance));
	final ExecutorService service = Executors.newFixedThreadPool( 10 );
	
	service.execute( new Runnable() {
	    public void run(){ portfolio.withdraw(true, 100); }
	});

	service.execute( new Runnable() {
	    public void run(){ portfolio.withdraw(false, 100); }
	});

	service.shutdown();

	Thread.sleep(4000);

	System.out.println("-------------------------------------");
	checkingBalance = portfolio.getCheckingBalance();
	savingBalance = portfolio.getSavingsBalance();

	System.out.println("Checking balance is " + checkingBalance);
	System.out.println("Savings balance is " + savingBalance);
	System.out.println("Total balance is: " + (checkingBalance + savingBalance));

	if ( checkingBalance + savingBalance < 1000 ) {
	    System.out.println( "Broke the constraint" );
	}

    } // end main

} // end class UsePortfolio
