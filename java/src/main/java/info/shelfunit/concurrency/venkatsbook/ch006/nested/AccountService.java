package info.shelfunit.concurrency.venkatsbook.ch006.nested;

import scala.concurrent.stm.japi.STM;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

// from Programming Concurrency on the JVM by Venkat Subramaniam

public class AccountService {

    public AccountService() {}

    public void transfer(final Account from, final Account to, final int amount) {
	STM.atomic( new Runnable() {
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

    public void transferAndPrintBalance(final Account from, final Account to, final int amount) {
	boolean result = true;
	System.out.println("\n----------------------------\n");
	System.out.println( "Before transfer" );
	System.out.println( "From account has $" + from.getBalance() );
	System.out.println( "To account has $" + to.getBalance() );

	try {
	    // how to call a non-static method from a static method
	    new AccountService().transfer( from, to, amount );
	} catch ( AccountOperationFailedException ex ) {
	    result = false;
	}
	
	System.out.println( "Result of transfer is " + (result ? " Pass" : " Fail") );
	System.out.println( "From account has $" + from.getBalance() );
	System.out.println( "To account has $" + to.getBalance() );
	
    } // end transferAndPrintBalance() 

    public static void main( final String[] args ) throws Exception {
	final Account account1 = new Account(2000);
	final Account account2 = new Account(100);
	
	final ExecutorService service = Executors.newSingleThreadExecutor();

	service.submit( new Runnable() {
	    public void run() {
		try{ Thread.sleep( 1000 ); } catch ( Exception e ) {}
		account2.deposit( 20 );
	    } // end run
	});
	service.shutdown();

	AccountService as = new AccountService();
	as.transferAndPrintBalance(account1, account2, 500);
	System.out.println( "About to make large transfer" );
	as.transferAndPrintBalance(account1, account2, 5000);

    } // end method main

} // end class Account
