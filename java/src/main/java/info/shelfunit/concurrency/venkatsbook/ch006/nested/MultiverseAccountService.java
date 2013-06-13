package info.shelfunit.concurrency.venkatsbook.ch006.nested;

import org.multiverse.api.references.*;
import org.multiverse.api.*;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

// from Programming Concurrency on the JVM by Venkat Subramaniam

public class MultiverseAccountService {

    public MultiverseAccountService() {}

    public void transfer(final MultiverseAccount from, final MultiverseAccount to, final int amount) {
	StmUtils.atomic( new Runnable() {
	    public void run() {
		System.out.println( "Attempting transfer" );
		to.deposit(amount);
		System.out.println( "Simulating delay" );
		try { Thread.sleep( 5000 ); } catch ( Exception ex ) {}
		System.out.println( "Uncommitted to-account balance after deposit: $" + to.getBalance() );
		from.withdraw( amount);
	    } // end run
	});
    } // end transfer

    public void transferAndPrintBalance(final MultiverseAccount from, final MultiverseAccount to, final int amount) {
	boolean result = true;
	System.out.println("\n----------------------------\n");
	System.out.println( "Before transfer" );
	System.out.println( "From account has $" + from.getBalance() );
	System.out.println( "To account has $" + to.getBalance() );

	try {
	    // how to call a non-static method from a static method
	    new MultiverseAccountService().transfer( from, to, amount );
	} catch ( AccountOperationFailedException ex ) {
	    result = false;
	}
	
	System.out.println( "Result of transfer is " + (result ? " Pass" : " Fail") );
	System.out.println( "From account has $" + from.getBalance() );
	System.out.println( "To account has $" + to.getBalance() );
	
    } // end transferAndPrintBalance() 

    public static void main( final String[] args ) throws Exception {
	final MultiverseAccount account1 = new MultiverseAccount(2000);
	final MultiverseAccount account2 = new MultiverseAccount(100);
	
	final ExecutorService service = Executors.newSingleThreadExecutor();

	service.submit( new Runnable() {
	    public void run() {
		try{ Thread.sleep( 1000 ); } catch ( Exception e ) {}
		account2.deposit( 20 );
	    } // end run
	});
	service.shutdown();

	MultiverseAccountService as = new MultiverseAccountService();
	as.transferAndPrintBalance(account1, account2, 500);
	System.out.println( "About to make large transfer" );
	as.transferAndPrintBalance(account1, account2, 5000);

    } // end method main

} // end class MultiverseAccount
